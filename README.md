CrudBook_Java17_SpringMVC_thymeleaf_JPA_MySql

Tecnologias usadas: 

- Java 17
- Spring boot, Thymeleaf (front-end), JPA Hibernate
- Banco de dados MySQL
- Padrão MVC

1 - Para executar a aplicação só precisará ter um banco de dados MySql localmente (ou via Docker) 
e criar uma base (banco de dados) conforme application.properties da solution (imagem em anexo do application.properties).

2 - Alterar as configurações do application.properties conforme sua base (nome, usuário e senha) 
e execute CrudbookApplication que fica na raiz do projeto "main > java > com.crudbook.crudbook", a partir disso as tabelas e relacionamentos serão geradas automaticamente.

3 - para verificar o projeto funcionando com front-end basta acessar http://localhost:8080/home

4 - Se desejar só usar a aplicação rest pode seguir as seguintes rotas abaixo:
(GET - consulta / consulta por id /{id}, POST - cadastro e edição/atualização) 

** localhost:8080/v1/livros

** localhost:8080/v1/livros/id 

** localhost:8080/v1/autores

** localhost:8080/v1/assuntos

5 - O relatório baixado estará no diretório "C:/temp/report.pdf"), busque pelo FileOutputStream("C:/temp/report.pdf") para alterar o caminho.
