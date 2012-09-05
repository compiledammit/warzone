<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
            <li><g:link controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
        </g:each>
    </ul>
</div>
