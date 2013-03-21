<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="colId" required="true" type="java.lang.Integer"%>
<%@ attribute name="selected" required="true" type="java.lang.Boolean"%>
<%@ attribute name="sortActionPrefix" required="true" type="java.lang.String"%>
<%@ attribute name="thLabel" required="true" type="java.lang.String"%>
<%@ attribute name="thFieldA" required="true" type="java.lang.String"%>
<%@ attribute name="thFieldD" required="true" type="java.lang.String"%>
<%@ attribute name="orderAsc" required="true" type="java.lang.String"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${selected}">
		<c:choose>
			<c:when test="${orderAsc}">
				<th class="col${colId} header headerSortUp"><a
					href="${sortActionPrefix}&sortBy=${thFieldD}">${thLabel}</a></th>
			</c:when>
			<c:otherwise>
				<th class="col${colId} header headerSortDown"><a
					href="${sortActionPrefix}&sortBy=${thFieldA}">${thLabel}</a></th>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<th class="col${colId} header"><a
			href="${sortActionPrefix}&sortBy=${thFieldA}">${thLabel}</a></th>
	</c:otherwise>
</c:choose>