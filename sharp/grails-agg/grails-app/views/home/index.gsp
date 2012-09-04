<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Grails Twitter Bootstrap Scaffolding</title>
</head>

<body>
<div class="row-fluid">

    <section id="main" class="span9">

        <div class="hero-unit">
            <h1>gr('ails blog')[aggregator]</h1>
        </div>

        <g:render template="/entry/entryListTemplate" model="[entries: entries, entriesTotal: entriesTotal]" />

    </section>

    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <ul class="nav nav-list">
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                    <li><g:link controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
                </g:each>
            </ul>
        </div>
    </aside>
</div>
</body>
</html>
