<#-- @ftlvariable name="factions" type="kotlin.collections.List<com.totalwar.warhammer.dataapp.models.Faction>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list factions?reverse as faction>
        <div>
            <h3>
                <a href="/factions/${faction.id}">${faction.name}</a>
            </h3>
            <p>
                ${faction.description}
            </p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/factions/new">Create faction</a>
    </p>
</@layout.header>