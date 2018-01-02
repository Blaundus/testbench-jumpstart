<center>
<a href="https://vaadin.com">
 <img src="https://vaadin.com/images/hero-reindeer.svg" width="200" height="200" /></a>
</center>

# TestBench Jumpstart
In this project we will start with TestBench from scratch and will extend it step by step


## Demo Apps
The demo App itself is always the same from the 
view the user will have on it. The only difference between 
the version **demo01** and **demo02** is the structure of the implementation itself.
So we can see, how different ways of organizing the code will
effect the tests and the handling itself.

### the demo app itself
The demo app is based on a Grid with a few informtions about some 
"people". This data is dynamically generated, no persistence store is needed to runn the app.
You can search for data sets with a keyword that must be written inside the text - field.
New entries can be created with the "Add new customer" button, that will 
activate the input form. 

Below you can see the app in action.

![_data/docu/images/JavaMagazin-TestingVaadin-001.gif](_data/docu/images/JavaMagazin-TestingVaadin-001.gif) 


### modules - demos - demo01
This is an very easy example app. Everything is in one package 
and really hard bound together.

### modules - demos - demo02
This is the same app as you could find in the demo01 module.
But here the structure is more like an industrial project.

### modules - demos - demo03
As before the app itself is the same, but now 
the code itself is optimized for industrial production like 
UI Testing. 



## modules - junit4
Below this module you will find junit tests for the demo-apps 
based on junit4. This tests are using the same [http://www.vaadin.com](http://www.vaadin.com) version as the 
junit5 module.

### modules - junit4 - m001
In the module **m001** you will find the tests for the demo01 - app

### modules - junit4 - m002
In the module **m002** you will find the tests for the demo02 - app

## modules - junit5

### modules - junit5 - m000
This is the junit5 Version that is using the demo01 Implementation,
only to show the initial step. 

### modules - junit5 - m001
In the module **m002** you will find the tests for the demo02 - app

### modules - junit5 - m002
In the module **m002** you will find the tests for the demo02 - app

### modules - junit5 - m003
In the module **m003** you will find the tests for the demo03 - app
Here I will show how to use the Vaadin AddOn 
[https://github.com/vaadin-developer/vaadin-addons](https://github.com/vaadin-developer/vaadin-addons)
and the optimized PageObject Pattern.

