/**
 * Created by shenp on 2017/3/28.
 */
var autocomplete_utils = {};

autocomplete_utils.buildData = function(sources, builder){
    var datas = [];
    if(sources != null){
        for (var i=0; i<sources.length; i++){
            var source = sources[i];
            var value = builder.getValue(source);
            var label = builder.getLabel(source);
            datas.push({label:label, value:value, data:source});
        }
    }
    return datas;
}



autocomplete_utils.init_combox = function(selector, selectedCallback){
    $( selector ).combobox({selectedCallback : selectedCallback});
}

$.widget( "custom.combobox", {

    _setOption: function (key, value) {
        this._super("_setOption", key, value);
    },
    _init: function() {
        var selected = this.element.children( ":selected" );
        var value = selected.val() ? selected.text() : "";
        this.input.val(value);
    },
    _setOptions: function (options) {
        var key;
        console.group('_setOptions');
        for (key in options) {
            this._setOption(key, options[key]);
        }
        console.groupEnd();

        return this;
    },

    _create: function() {
        this.wrapper = $( "<span>" )
            .addClass( "custom-combobox" )
            .insertAfter( this.element );
        this.element.hide();
        this._createAutocomplete();
        //this._createShowAllButton();
    },
    _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
            value = selected.val() ? selected.text() : "";
        this.input = $( "<input>" )
            .appendTo( this.wrapper )
            .val( value )
            .attr( "title", "" )
            .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
            .autocomplete({
                delay: 0,
                minLength: 0,
                source: this.options.source !=null?this.options.source:$.proxy( this, "_source" )
            })
            .tooltip({
                classes: {
                    "ui-tooltip": "ui-state-highlight"
                }
            });
        var mInput = this.input;
        this.input.on('click', function(){
            mInput.autocomplete( "search", "" );
        });
        var autocompleteselect = function( event, ui ) {
            ui.item.option.selected = true;
            this._trigger( "select", event, {
                item: ui.item.option
            });
            if(this.options.selectedCallback != null){
                this.options.selectedCallback(ui);
            }
        }
        if(this.options.onselect != null){
            autocompleteselect = this.options.onselect;
        }
        this._on( this.input, {
            autocompleteselect: autocompleteselect,

            autocompletechange: "_removeIfInvalid"
        });
    },

    _createShowAllButton: function() {
        var input = this.input,
            wasOpen = false;

        $( "<a>" )
            .attr( "tabIndex", -1 )
            .attr( "title", "" )
            .tooltip()
            .appendTo( this.wrapper )
            .button({
                icons: {
                    primary: "ui-icon-triangle-1-s"
                },
                text: false
            })
            .removeClass( "ui-corner-all" )
            .addClass( "custom-combobox-toggle ui-corner-right" )
            .on( "mousedown", function() {
                wasOpen = input.autocomplete( "widget" ).is( ":visible" );
            })
            .on( "click", function() {
                input.trigger( "focus" );

                // Close if already visible
                if ( wasOpen ) {
                    return;
                }

                // Pass empty string as value to search for, displaying all results
                input.autocomplete( "search", "" );
            });
    },

    _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        response( this.element.children( "option" ).map(function() {
            var text = $( this ).attr('label');
            var value = $( this ).attr('sValue');
            if ( this.value && ( !request.term || matcher.test(text) ) )
                return {
                    label: text,
                    value: value,
                    option: this
                };
        }) );
    },

    _removeIfInvalid: function( event, ui ) {

        // Selected an item, nothing to do
        if ( ui.item ) {
            return;
        }

        // Search for a match (case-insensitive)
        var value = this.input.val(),
            valueLowerCase = value.toLowerCase(),
            valid = false;
        this.element.children( "option" ).each(function() {
            if ( $( this ).text().toLowerCase() === valueLowerCase ) {
                this.selected = valid = true;
                return false;
            }
        });

        // Found a match, nothing to do
        if ( valid ) {
            return;
        }

        // Remove invalid value
        this.element.val( "" );
        this.element.attr('value', '');
        this.input
            .val( "" )
            .attr( "title", "请选择" )
            .tooltip( "open" );
        this._delay(function() {
            this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.autocomplete( "instance" ).term = "";
    },

    _destroy: function() {
        this.wrapper.remove();
        this.element.show();
    }
});
