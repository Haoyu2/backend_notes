
Have done:
- the swagger-ui is configured, but it conflicts with controller adivce
- details controller for query weather by city name
    - there are Feign and Resttemplate implementations
    - for requst weather by ids of city, the completablefuture is used
- search controller for query multiple cities weather. Completablefuture is used

- euraka can register details and search service




Unfinished
- I have spend a lot time to configure swagger-ui, but cannot solve it. then I tried to 
create the module from scratch to see where went wrong, I find whenever 
 the controlleradvice is defined then the swagger-ui always goes to a petstore, I cannot figure it out 
- My gateway pom cannot resolve the gate dependency, I have not figure it out.