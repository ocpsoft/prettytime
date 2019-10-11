prettytime
==========
[![Badge](http://www.libtastic.com/static/osbadges/152.png)](http://www.libtastic.com/technology/152/)

Social Style Date and Time Formatting for Java

Learn more at http://ocpsoft.org/prettytime/

Android
=======
To use prettytime in android, first add the following dependency to your app level build.gradle

	implementation 'org.ocpsoft.prettytime:prettytime:4.0.1.Final'

ProGuard rules are automatically configured for you if you use the R8 shrinker (which is the default since version 3.4.0 of the Android Gradle Plugin). If you are using an older version of AGP, add the ProGuard rules from [this file](core/src/main/resources/META-INF/proguard/prettytime.pro)

DEVELOPMENT
===========
force-release: mvn release:prepare release:perform -DskipTests=true -DdevelopmentVersion=4.0.2-SNAPSHOT -DreleaseVersion=4.0.1.Final -Dtag=4.0.1.Final -Darguments="-DskipTests=true -Dmaven.test.skip=true"
