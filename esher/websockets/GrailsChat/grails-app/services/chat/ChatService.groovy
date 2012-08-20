package chat

import org.atmosphere.cpr.*
import org.atmosphere.cpr.AtmosphereResource.TRANSPORT

class ChatService {

    static transactional = false
    static atmosphere = [mapping: '/atmosphere/chatty']

    def onRequest = { event ->
        println "Inside onRequest!"
        try {
            AtmosphereRequest req = event.request
            println req
            println req.method
            if (req.method.equalsIgnoreCase("GET")) {
                println 'Suspending'
                event.suspend()
            } else if (req.method.equalsIgnoreCase("POST")) {
                String stuff = req.reader.readLine().trim()
                println "Stuff is $stuff"
                event.broadcaster.broadcast(stuff)
            }
        } catch (Exception e) {
            println "ERROR!!!!!"
        }

    }

    def onStateChange = { event ->
        println "Inside onStateChange!"
        println "Event is $event"
        AtmosphereResource r = event.resource
        AtmosphereResponse res = r.response

        try {
            if (event.isSuspended()) {
                String body = event.message.toString()
                String author = body.substring(body.indexOf(":") + 2, body.indexOf(",") - 1);
                String message = body.substring(body.lastIndexOf(":") + 2, body.length() - 2);
                res.writer.write( createMessage(author, message) )

                switch (r.transport()) {
                    case TRANSPORT.JSONP:
                    case TRANSPORT.LONG_POLLING:
                        event.resource.resume()
                        break
                    default:
                        res.writer.flush()
                }
            } else if (!event.isResuming()) {
                event.broadcaster().broadcast( createMessage('someone', 'buh bye') )
            }
        } catch (Exception e) {
            println "ERROR in onStateChange: $e"
        }
    }

    private String createMessage(String author, String text) {
        return "{ \"text\" : \"" + text + "\", \"author\" : \"" + author + "\" , \"time\" : " + new Date().time + "}"
    }
}
