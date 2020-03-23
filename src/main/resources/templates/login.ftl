<#import "parts/common.ftl" as common>
<#import "parts/form.ftl" as form>
<@common.page>
    <@form.login "/login" />
    <a href="/registration">Add new user</a>
</@common.page>