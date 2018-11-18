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
		<!--<h1>Hello <c:out value="${ name }" /></h1>-->
		     <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <!--<th>Lastname</th>-->
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
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
	</jsp:body>
</tags:template>