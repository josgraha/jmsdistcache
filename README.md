# hedgerepo

I'm an app. Or maybe I'm a library? I haven't decided yet. 

The choice is up to you!

## Setup
* start mysql
* start activemq on default port
* seed the database via hibernate.cfg or the sql scripts
* disable autocreation of schema in hibernate.cfg is hiberate initialized db

## Usage

run server 1 using run_server_XX script <port1>
edit spring-context.xml
set property of p:cacheOnly="true"

run server 2 using run_server_XY script <port2>

load the entry via cache only via webservice on server 1
http://localhost:<port2>/jmsdistcache/employee/1

### Expected Result:
No value

create an entry in the employee, department, and employee_department table

load the entry into the cache via webservice on server 2
http://localhost:<port2>/jmsdistcache/employee/1

load the entry via cache only via webservice on server 1
http://localhost:<port2>/jmsdistcache/employee/1

### Expected Result:
Value loaded via cache on server2 and replication via JMS to server 1


## TODO
Create a unit test to capture the above steps.


## Known Issues
Requires jms-replication version 0.5 dependency.  Will throw NoSuchMethod exceptions on older versions.  Notice the repository location added to the maven build.

## License

Copyright © 2014 Joe Graham

Distributed under the Eclipse Public License, the same as Clojure.
