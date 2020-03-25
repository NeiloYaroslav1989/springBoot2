<#import "parts/common.ftl" as common>
<#import "parts/form.ftl" as form>
<@common.page>
    <p>${message?ifExists}</p>
    <@form.login "/login" false/>
</@common.page>