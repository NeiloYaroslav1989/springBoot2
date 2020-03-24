<#macro login path isRegisterForm>
    <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-4">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <#if !isRegisterForm>
            <a href="/registration">Add new user</a>
        </#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sing In</#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit" >Sign Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>