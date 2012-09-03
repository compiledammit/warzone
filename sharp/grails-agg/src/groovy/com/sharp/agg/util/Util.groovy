package com.sharp.agg.util

/**
 * Created with IntelliJ IDEA.
 * User: 558848
 * Date: 9/2/12
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
class Util {
    def stripTags( str )
    {
        return str.replaceAll("<(.|\n)*?>", '')
    }

    def left( str, chars )
    {
        return str.substring(0,Math.min(str.length(), chars))
    }
}
