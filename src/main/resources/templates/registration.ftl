<#import "parts/common.ftl" as common>
<#import "parts/form.ftl" as form>
<@common.page>
    <h1>Add new User</h1>
    <p>${message?ifExists}</p>
    <@form.login "/registration" />
</@common.page>