<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create Faction</h3>
        <form action="/factions" method="post">
            <p>
                <input type="text" name="name">
            </p>
            <p>
                <textarea name="description"></textarea>
            </p>
            <p>
                <input type="submit">
            </p>
        </form>
    </div>
</@layout.header>
