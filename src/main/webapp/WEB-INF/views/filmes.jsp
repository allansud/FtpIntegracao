<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here
		</script>
  	</jsp:attribute>
	<jsp:body>
	
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
      <div class="container">
        <a class="navbar-brand" href="#">Listagem de Filmes</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
		<!--<h1>Hello <c:out value="${ name }" /></h1>-->
		<h1>Filmes</h1>
		     <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Tamanho</th>
                        <!--<th>Email/login</th> -->
                        <!--<th>Profession</th> -->
                        <!--<th>Select</th> -->
					<th>                    				
				</tr>
                </thead>
                <tbody>
                    <c:forEach var="filme" items="${ listaFilmes }">
                        <tr>
                            <td>${filme.nome}</td>
                            <td>${filme.tamanho}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h1>Seriados</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Tamanho</th>
                        <!--<th>Email/login</th> -->
                        <!--<th>Profession</th> -->
                        <!--<th>Select</th> -->
					<th>                    				
				</tr>
                </thead>
                <tbody>
                    <c:forEach var="seriado" items="${ listSeriados }">
                        <tr>
                            <td>${seriado.nome}</td>
                            <td>${seriado.tamanho}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
	</jsp:body>
</tags:template>