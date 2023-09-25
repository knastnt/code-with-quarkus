Цепочка вложенных ресурсов со скоупом RequestScope.
Передача параметров через HttpServerRequest, т.к. UriInfo.getPathParameters() почему-то теряет параметры,
а @PathParam на поле класса не работает.

Смотри комменты в Child1Resource

http://127.0.0.1:8085/hello/id111/id222/id333