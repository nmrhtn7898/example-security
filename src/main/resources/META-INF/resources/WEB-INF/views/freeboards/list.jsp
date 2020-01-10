<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-8 text-left">
    <h1>자유게시판</h1>
    <table>
        <tr>
            <th width="60%"><a class="sort" data-sort-by="title" href="javascript:void(0)">제목</a></th>
            <th width="20%"><a class="sort" data-sort-by="creator_writer" href="javascript:void(0)">작성자</a></th>
            <th width="20%"><a class="sort" data-sort-by="creator_writeDate" href="javascript:void(0)">작성일</a></th>
        </tr>
            <tr>
                <td><span>제목</span></td>
                <td><span>작성자</span></td>
                <td><span>작성일</span></td>
            </tr>
    </table><br>

    <button id="write">글쓰기</button><br><br>

    <div>페이징 부분</div><br>

    <div align="center">
        <form action="#" method="get">
            <select>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="titleOrContent">제목+내용</option>
                <option value="writer">작성자</option>
            </select>
            <input type="search" width="500"/>
            <input type="submit" value="검색"/>
        </form>
    </div><br><br>

    <%--<input type="hidden" id="currentPage" value="${list.page}"/>
    <input type="hidden" id="currentBlockSize" value="${list.blockSize}"/>
    <input type="hidden" id="currentPageSize" value="${list.pageSize}"/>
    <input type="hidden" id="currentBlock" value="${list.currentBlock}"/>
    <input type="hidden" id="currentSearchType" value="${list.searchType}"/>
    <input type="hidden" id="currentSearchText" value="${list.searchText}"/>
    <input type="hidden" id="currentSortBy" value="${list.sortBy}"/>
    <input type="hidden" id="currentSortDirection" value="${list.sortDirection}"/>--%>

</div>