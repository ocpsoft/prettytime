prettytime
==========
Social Style Date and Time Formatting for Java

Learn more at https://www.ocpsoft.org/prettytime/

Java >=1.8 (Support JDK DateTime API)
=======
PrettyTime requires Java/JDK >=1.8 as of version `5.0.0.Final` or higher.

Java <=1.7
=======
Users requiring versions of Java/JDK less than or equal to 1.7 should use PrettyTime versions in the 4.x branch: `4.0.6.Final` or lower.

Android
=======
To use prettytime in android, first add the following dependency to your app level build.gradle

	implementation 'org.ocpsoft.prettytime:prettytime:5.0.4.Final'

ProGuard rules are automatically configured for you if you use the R8 shrinker (which is the default since version 3.4.0 of the Android Gradle Plugin). If you are using an older version of AGP, add the ProGuard rules from [this file](core/src/main/resources/META-INF/proguard/prettytime.pro)

Be sure to check maven central for the latest version: https://repo1.maven.org/maven2/org/ocpsoft/prettytime/prettytime/

**Note**: To use prettytime in projects with a `minSdkVersion` below 26, [API desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring) is required.

DEVELOPMENT
===========
export RELEASE_VERSION="5.x.x.Final"
export SNAPSHOT_VERSION="5.x.x-SNAPSHOT"

force-release: mvn release:prepare release:perform -DskipTests=true -DdevelopmentVersion=$SNAPSHOT_VERSION -DreleaseVersion=$RELEASE_VERSION -Dtag=$RELEASE_VERSION -Darguments="-DskipTests=true -Dmaven.test.skip=true"

CODE FORMAT DEFINITIONS/CONFIG
==============================
Please use the following eclipse-style code formatter settings when submitting PRs:

https://github.com/ocpsoft/common/blob/master/ocpsoft-eclipse-code-format.xml
