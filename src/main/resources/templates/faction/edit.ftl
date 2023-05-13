<#-- @ftlvariable name="faction" type="com.totalwar.warhammer.dataapp.models.Faction" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Edit faction</h3>
        <form action="/factions/${faction.id}" method="post">
            <p>
                <input type="text" name="name" value="${faction.name}">
            </p>
            <p>
                <textarea name="description">${faction.description}</textarea>
            </p>
            <p>
                <input type="submit" name="_action" value="update">
            </p>
        </form>
    </div>
    <div>
        <form action="/factions/${faction.id}" method="post">
            <p>
                <input type="submit" name="_action" value="delete">
            </p>
        </form>
    </div>
</@layout.header>
