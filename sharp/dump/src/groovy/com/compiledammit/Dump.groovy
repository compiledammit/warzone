package com.compiledammit

import javax.servlet.http.HttpServletRequest

/**
 * Created with IntelliJ IDEA.
 * User: 558848
 * Date: 8/23/12
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
class Dump {

    def dump(variable) {
        def processed = []
        def output = ''
        def doDump

        doDump = { var ->
            def code = var.hashCode()
                    if( processed.indexOf(code) > -1 )
                    {
                        return
                    }
                    else {
                        processed.add(code)
                    }
                    def type = 'class'
                    def clazz = var.getClass()

                    if (ArrayList.isAssignableFrom(clazz)) {
                        type = 'list'
                    }
                    else if (Map.isAssignableFrom(clazz)) {
                        type = 'map'
                    }

                    def props = var.properties
                    def innerOutput = ''
                    innerOutput += '<table class="cfdump_table_' + type + '">'

                    // name row
                            innerOutput += '<tr>'
                                innerOutput += '<th colspan="2" class="cfdump_th_'+ type +'">' + clazz.name + '</th>'
                            innerOutput += '</tr>'

                            innerOutput += '<tr>'
                                innerOutput += '<td colspan="2" class="cfdump_th_' + type+'">'
                                    innerOutput += 'Properties:'
                                innerOutput += '</td>'
                                innerOutput += '<tr><td colspan="2">'
                                    //each property
                                    innerOutput += '<table class="cfdump_table_' + type + '" style="width: 100%">'
                                        var.properties.eachWithIndex { p, idx ->
                                            innerOutput += '<tr>'
                                                innerOutput += '<td class="cfdump_th_' + type+'">' + p.key + '</td>'
                                                innerOutput += '<td class="cfdump_td_' + type+'">' + p.value.toString() + '</td>'
                                            innerOutput += '</tr>'
                                        }
                                    innerOutput += '</table>'
                                innerOutput += '</td></tr>'
                            innerOutput += '</tr>'

                            innerOutput += '<tr>'
                                innerOutput += '<td colspan="2" class="cfdump_th_' + type+'">'
                                    innerOutput += 'Value:'
                                innerOutput += '</td>'
                                innerOutput += '<tr><td colspan="2">'
                                    innerOutput += '<table class="cfdump_table_' + type + '" style="width: 100%">'
                                    if( !isSimpleValue(var) ){
                                        var.eachWithIndex{ v, idx ->
                                                innerOutput += '<tr>'
                                                    def key = v.hasProperty('key') ? v.key + ' : ' : idx
                                                    def val = v.hasProperty('value') ? isSimpleValue(v.value) ? v.value : owner.call(v.value) : isSimpleValue(v) ? v : owner.call(v)
                                                    innerOutput += '<td class="cfdump_th_' + type+'">' + key + '</td>'
                                                    innerOutput += '<td class="cfdump_td_' + type+'">' + val + '</td>'
                                                 innerOutput += '</tr>'
                                        }
                                    }
                                    else{
                                        innerOutput += '<tr>'

                                            innerOutput += '<td class="cfdump_property cfdump_td_value">'
                                                innerOutput += isSimpleValue( var ) ? var ? var.toString() : '' : doDump( var )
                                            innerOutput += '</td>'

                                        innerOutput += '</tr>'
                                    }

                                    innerOutput += '</table>'
                                innerOutput += '</td></tr>'
                            innerOutput += '</tr>'

                            innerOutput += '<tr>'
                                innerOutput += '<td colspan="2" class="cfdump_th_' + type+'">'
                                    innerOutput += 'Methods:'
                                innerOutput += '</td>'
                                innerOutput += '<tr><td colspan="2">'
                                        innerOutput += '<table class="cfdump_table_' + type + '" style="width: 100%">'
                                                if( clazz.hasProperty('declaredMethods') ){
                                                    clazz.declaredMethods.findAll{ m ->
                                                        innerOutput += '<tr>'

                                                            innerOutput += '<td class="cfdump_td_' + type+'">'
                                                                innerOutput +=  m.toString()
                                                            innerOutput += '</td>'

                                                        innerOutput += '</tr>'
                                                    }
                                                }

                                        innerOutput += '</table>'
                                innerOutput += '</td></tr>'
                            innerOutput += '</tr>'
                    innerOutput += '</table>'
                return innerOutput
        }
        output = doDump(variable)

        return getStyles() + output
    }

    private isSimpleValue(var) {
        def clazz = var.getClass()
        // probably need more here
        if (int.isAssignableFrom(clazz) || String.isAssignableFrom(clazz) || Number.isAssignableFrom(clazz) || Boolean.isAssignableFrom(clazz)) {
            return true
        }
        return false
    }

    private getStylez()
    {
        return [list:'']
    }
    private getStyles() {
        def fontSize = 'small'
        def styles = '''
<script>function dumpToggle(id){var ele = document.getElementById(id); if(ele.style.display=='block'){ele.style.display='none';}else{ele.style.display='block';}}</script>

<style type=\"text/css\">
.cfdump_subtitle,.cfdump_property,cfdump_title{list-style: none; margin-left: 20px;}
.cfdump_table { cell-spacing: 2; background-color: #cccccc }
.cfdump_th { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #666666 }
.cfdump_td_name { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #e0e0e0; vertical-align: top }

// shared by all
.cfdump_td_value { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #ffffff; vertical-align: top }

// map (blue)
.cfdump_table_map { cell-spacing: 2; background-color: #336699 }
.cfdump_th_map { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #3366cc }
.cfdump_td_map { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #99ccff; vertical-align: top }

// list (green)
.cfdump_table_list { cell-spacing: 2; background-color: #006600 }
.cfdump_th_list { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #009900 }
.cfdump_td_list { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #ccffcc; vertical-align: top }

// binary (yellow)
.cfdump_table_binary { cell-spacing: 2; background-color: #ff6600 }
.cfdump_th_binary { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #ff9900 }

// class (red)
.cfdump_table_class { cell-spacing: 2; background-color: #990000 }
.cfdump_th_class { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #cc3300 }
.cfdump_td_class { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #ffcccc; vertical-align: top }

// query (purple)
.cfdump_table_query { cell-spacing: 2; background-color: #990066 }
.cfdump_th_query { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #993399 }
.cfdump_td_query { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #ffccff; vertical-align: top }

// xml (gray)
.cfdump_table_xml { cell-spacing: 2; background-color: #666666 }
.cfdump_th_xml { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #999999 }
.cfdump_td_xml { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: black; text-align: left; padding: 3px; background-color: #dddddd; vertical-align: top }

// udf (brown)
.cfdump_table_udf { cell-spacing: 2; background-color: #660033 }
.cfdump_th_udf { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #996633 }
.cfdump_td_udf { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; font-style: italic; color: black; text-align: left; padding: 3px; background-color: #ffffff; vertical-align: top }
.cfdump_table_udf_args { cell-spacing: 2; background-color: #996600 }
.cfdump_th_udf_args { font-size: ''' + fontSize + '''; font-family: verdana, arial, helvetica, sans-serif; color: white; text-align: left; padding: 5px; background-color: #cc9900 }
</style>
'''
        return styles
    }
}
