<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${param.selected}">
		<c:choose>
			<c:when test="${param.orderAsc}">
				<th class="col${param.colId} header headerSortUp"><a
					href="${param.sortActionPrefix}&sortBy=${param.thField}1">${param.thLabel}</a></th>
			</c:when>
			<c:otherwise>
				<th class="col${param.colId} header headerSortDown"><a
					href="${param.sortActionPrefix}&sortBy=${param.thField}0">${param.thLabel}</a></th>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<th class="col${param.colId} header"><a
			href="${param.sortActionPrefix}&sortBy=${param.thField}1">${param.thLabel}</a></th>
	</c:otherwise>
</c:choose>