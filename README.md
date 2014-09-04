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

Fork notes
----------
- This `grails-miniprofiler` version (0.3.1) is my fork of main development stream with few enhancements (see changelog). I have use it in production together with underlying implementation
  of `miniprofiler-jvm` v0.3.1 (also my fork) plugin (available at https://github.com/dmurat-miniprofiler-jvm/tree/myModifications).
- This fork is intended to work with grails 2.3.x. For later grails versions (i.e. 2.4.x) please look at latest version at original project github page - https://github.com/tomdcc/grails-miniprofiler
- Source code for this fork is available at https://github.com/dmurat/grails-miniprofiler/tree/myModifications (all changes are in myModification branch).
- Released version for this fork is available in maven compatible repository at https://github.com/dmurat/mvn-repo/raw/master/releases/, so make sure that you have it included in yours build
  configuration.
- Most probably, this is last version which will be released. However, there might be a version which will be a fork for 0.4.1 version (or later) of original project. Currently I don't have any plans
  to further maintain this particular fork.
- I have submitted pull requests of all my changes to the original project. However, author ignored them and did not included them in original source tree. So I was somewhat forced to publish this
  fork with primary intention to make my life somewhat easier.

Fork changelog
--------------
### 0.3.1 - forked
- Support for multiple data sources.
- Support for configuring various miniprofiler's GUI display options.
- Support for configuring keybord shortcut for toggling miniprofiler's popup (Ctrl+M) by default.
- Some minor JavaScript fixes to imporve stability.
