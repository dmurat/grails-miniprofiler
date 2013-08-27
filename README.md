Grails Miniprofiler plugin
==========================

[![Build Status](https://travis-ci.org/tomdcc/grails-miniprofiler.png)][1]

This plugin provides the functionality of the StackExchange [.NET MiniProfiler][2] for Grails applications.

It uses the functionality of the [Grails Profiler plugin][3] along with SQL intrumentation using [log4jdbc][4] to shows timing information and sql query information in a heads-up display in a web page, useful for debugging database and other performance problems.

Installation
------------

This plugin also requires the Profiler Plugin to be installed:

    plugins {
        // etc
        runtime ':profiler:0.4.1'
        runtime ':miniprofiler:0.1'
    }

NOTE: at time of writing the currently released profiler plugin (0.4) has bugs with Grails 2.2+. Until an updated version is released, you'll need to grab it from source at [GitHub][6] and either install it locally using `grails package-plugin` in the grails-profiler directory, followed by `grails install-plugin /path/to/grails-profiler/grails-profiler-0.4.1.zip` in your grails application directory, or deploy it to a local maven repo if you have one of those.

You should then add the following to the bottom of any layouts that you would like to see the profiling information on, just inside the bottom of the html body:

        <!-- rest of layout above -->
        <miniprofiler:javascript/>
        </body>
    </html

Usage
-----

This plugin adds a clickable overlay onto your website as per the Stack Exchange MiniProfiler. Your controller methods, views and service methods are all visible, and any SQL queries executed during each of those calls can be seen.
See the talk from the Groovy & Grails Exchange 2012 which introduced the plugin [here][5].


Known Issues
------------
 - Currently, the plugin depends on the current version of the profiler plugin from github, the last version 0.4 does not work well with Grails 2.2+
 - At time of writing, the profiler plugin does not correctly wrap controller methods which are not closures, so controller methods may or may not appear correctly in the output.

This is very early code, all bug reports and suggestions very welcome!

Changelog
---------

### 0.3 ###
 - Split core functionality into miniprofiler-jvm project
 - Fix several bugs, including layout timing on Grails 2.2.x

### 0.2 ###
 - Profile AJAX requests
 - Use log4jdbc from Maven Central, now that it's published there

### 0.1 ###
 - Initial release

[1]:https://travis-ci.org/tomdcc/grails-miniprofiler
[2]:http://miniprofiler.com/
[3]:http://grails.org/plugin/profiler
[4]:https://code.google.com/p/log4jdbc/
[5]:http://skillsmatter.com/podcast/groovy-grails/debugging-grails-database-performance/te-6299
[6]:https://github.com/pledbrook/grails-profiler
