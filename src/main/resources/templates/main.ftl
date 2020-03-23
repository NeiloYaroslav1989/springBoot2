<#import "parts/common.ftl" as common>
<#import "parts/form.ftl" as form>
<@common.page>
    <p>Get your greeting <a href="/greeting">here</a></p>
    <br>
    <@form.logout />
    <div><a href="/user">User list</a></div>
    <br>
    <div>
        <form method="post" action="message" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите текст сообщения">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="file" name="file">
            <button type="submit">Добавить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </div>
    <p>Список сообщений:</p>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>

    <#list messages as messages>
        <div>
            <b>${messages.id}</b>
            <span>${messages.text}</span>
            <i>${messages.tag}</i>
            <strong>${messages.authorName}</strong>
            <div>
                <#if messages.filename??>
                    <img src="/img/${messages.filename}">
                </#if>
            </div>
        </div>
    <#else>No messages
    </#list>
</@common.page>