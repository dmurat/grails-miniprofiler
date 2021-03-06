package grails.plugin.miniprofiler

import geb.spock.GebReportingSpec
import io.jdev.miniprofiler.test.pages.MiniProfilerGapModule
import io.jdev.miniprofiler.test.pages.MiniProfilerQueryModule
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import spock.lang.IgnoreIf

@IgnoreIf({ ConfigurationHolder.getConfig()?.grails?.profiler?.disable as boolean })
class MiniprofilerFunctionalSpec extends GebReportingSpec {

	def "can see miniprofiler"() {
		when:
			to BookListPage

		then: 'mini profiler visible with single timing info'
			miniProfiler
			miniProfiler.results.size() == 1
			def result = miniProfiler.results[0]
			result.button.time ==~ ~/\d+\.\d ms/

		and: 'popup not visible'
			!result.popup.displayed

		when: 'click button'
			result.button.click()

		then: 'popup visible'
			result.popup.displayed

		and: ''
			def timings = result.popup.timings
			timings.size() == 4
			timings.label ==  ['/test22/book/list', 'BookController.list', 'View', 'Layout']
			timings.indent == [0, 1, 1, 1]
			timings.queries*.text() == [null, '2 sql', null, null]

		when: 'click sql link'
			timings[1].queries.click()

		then: 'three timings, but trivial gaps not visible'
			result.queriesPopup.displayed
			def queries = result.queriesPopup.queries
			queries.size() == 5
			queries[0] instanceof MiniProfilerGapModule
			queries[0].displayed == !queries[0].trivial
			queries[1] instanceof MiniProfilerQueryModule
			queries[1].displayed
			queries[2] instanceof MiniProfilerGapModule
			queries[2].displayed == !queries[2].trivial
			queries[3] instanceof MiniProfilerQueryModule
			queries[3].displayed
			queries[4] instanceof MiniProfilerGapModule
			queries[4].displayed == !queries[4].trivial
	}
}
