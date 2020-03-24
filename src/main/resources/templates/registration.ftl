<#import "parts/common.ftl" as common>
<#import "parts/form.ftl" as form>
<@common.page>
    <div class="mr-4">Add new User</div>
    <p>${message?ifExists}</p>
    <@form.login "/registration" true/>
</@common.page>