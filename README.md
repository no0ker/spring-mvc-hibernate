1. change postgresql's connection properties in WEB-INF/mvc-dispatcher-servlet.xml
  <prop key="hibernate.connection.driver_class">org.postgresql.Driver</prop>
  <prop key="hibernate.connection.url">jdbc:postgresql://localhost:5432/postgres</prop>
  <prop key="hibernate.connection.username">postgres</prop>
  <prop key="hibernate.connection.password">12345</prop>
