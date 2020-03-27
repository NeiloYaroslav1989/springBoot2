<#import "parts/common.ftl" as common>

<@common.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group">
            <form method="post" action="message" enctype="multipart/form-data">
                <input type="text" name="text" class="form-control mt-2 ${(textError??)?string('is-invalid', '')}"
                       placeholder="Введите сообщение" value="<#if message??>${message.text}</#if>" >
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
                <input type="text" name="tag" class="form-control mt-2" placeholder="Тэг"
                       value="<#if message??>${message.text}</#if>">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
                <div class="custom-file mt-2">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <button type="submit" class="btn btn-primary mt-2">Добавить</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list messages as messages>
            <div class="card my-3">
                <#if messages.filename??>
                    <img src="/img/${messages.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${messages.text}</span>
                    <i>${messages.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${messages.authorName}
                </div>

            </div>
        <#else>No messages
        </#list>
    </div>
</@common.page>