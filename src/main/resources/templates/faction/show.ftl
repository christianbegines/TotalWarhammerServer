<#-- @ftlvariable name="faction" type="com.totalwar.warhammer.dataapp.models.Faction" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            ${faction.name}
        </h3>
        <p>
            ${faction.description}
        </p>
        <hr>
        <p>
            <a href="/factions/${faction.id}/edit">Edit faction</a>
        </p>
    </div>
</@layout.header>
