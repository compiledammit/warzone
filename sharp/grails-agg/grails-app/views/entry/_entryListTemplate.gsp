<g:each in="${entries}" var="entry">
    <div class="well">
        <div class="row-fluid">
            <div class="span12">
                <h2><g:link controller="entry" action="go" id="${entry.id}" target="_blank">${entry.title}</g:link></h2>

                <div>
                    <p>
                        <g:link controller="entry" action="byFeed" id="${entry.feed.id}">${entry.feed.title}</g:link>
                    &bull;
                        <prettytime:display date="${entry.postedOn}"/>
                    &bull;
                        <g:each in="${entry.categories}" var="cat" status="s">
                            <g:link controller="entry" action="byCategory" id="${cat.id}">${cat.category}</g:link><g:if test="${s != entry.categories.size() - 1}">,</g:if>
                        </g:each>
                    </p>
                </div>
            </div>
        </div>

        <p>
            ${entry.contents.encodeAsHTML()}
        </p>
    </div>
</g:each>

<div class="pagination">
    <bootstrap:paginate action="${action ?: 'index'}" id="${id ?: ''}" total="${entriesTotal}"/>
</div>