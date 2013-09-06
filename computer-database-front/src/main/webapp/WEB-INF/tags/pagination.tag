<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="lbl"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"%>
<%@ attribute name="resultsPerPage" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="pageCount" required="true" type="java.lang.Integer"%>
<%@ attribute name="numberOfElements" required="true" type="java.lang.Integer"%>
<%@ attribute name="totalElements" required="true" type="java.lang.Integer"%>
<%@ attribute name="actionPrefix" required="true"
	type="java.lang.String"%>

<ul>

	<c:if test="${ page == 1 }">
		<li class="prev disabled"><a>&larr; <lbl:message
					code="form.pagination.previous" /></a></li>
	</c:if>
	<c:if test="${ page > 1 }">
		<li class="prev "><a href="${actionPrefix}&page=${page-1}">&larr;
				<lbl:message code="form.pagination.previous" />
		</a></li>
	</c:if>

	<li class="current"><a><lbl:message
				code="form.pagination.display"
				arguments="${(page-1)*resultsPerPage+1},${(page-1)*resultsPerPage+numberOfElements},${totalElements}" /></a>
	</li>

	<c:if test="${ page == pageCount }">
		<li class="next disabled"><a><lbl:message
					code="form.pagination.next" /> &rarr;</a></li>
	</c:if>
	<c:if test="${ page != pageCount }">
		<li class="next "><a href="${actionPrefix}&page=${page+1}"><lbl:message
					code="form.pagination.next" /> &rarr;</a></li>
	</c:if>

</ul>