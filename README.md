prettytime
==========
[![Badge](http://www.libtastic.com/static/osbadges/152.png)](http://www.libtastic.com/technology/152/)

Social Style Date and Time Formatting for Java

Learn more at http://ocpsoft.org/prettytime/

Android
=======
To use prettytime in android, first add the following dependency to your app level build.gradle 

	compile 'org.ocpsoft.prettytime:prettytime:4.0.1.Final'

When using Android with ProGuard, you must add the following to your ProGuard configuration script:

	-keep class org.ocpsoft.prettytime.i18n.**

DEVELOPMENT
===========
force-release: mvn release:prepare release:perform -DskipTests=true -DdevelopmentVersion=4.0.2-SNAPSHOT -DreleaseVersion=4.0.1.Final -Dtag=4.0.1.Final -Darguments="-DskipTests=true -Dmaven.test.skip=true"
