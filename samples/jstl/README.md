## This folder contains PrettyTime JSTL integration sample

To use PrettyTime JSTL tag, you simply have to :

* Declare PrettyTime JSTL tag library in your JSP :

```
<%@ taglib prefix="ocpsoft" uri="http://ocpsoft.org/prettytime/tags" %>
```

* Use the tag in your JSP :

```
<ocpsoft:prettytime date="${myDate}" locale="en_US"/>
```

PrettyTime JSTL tag parameters are described in the table below :

| Parameter     | Required | Description | Default value                                                |
|:-----------   |:--------:|:------------------------------------------ |:------------------------------|
| date          | yes      |    The date object to pretty print         | N/A                           |
| locale        | no       |    The locale used to localize the message | default locale                |

This module provides a single JSP web application that shows how to use PrettyTime JSTL tag library.

To launch the application, run the following command:

<code>mvn install && mvn exec:java -PrunJstlSample</code>

Then browse the following URL:

<code>http://localhost:8080/index.do</code>

You should see the following output:

```
PrettyTime JSTL Integration Sample

PrettyTime date : moments ago
```