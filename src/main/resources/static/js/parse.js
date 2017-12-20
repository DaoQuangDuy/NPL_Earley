$(document).ready(function(){

		Vue.component("word-input-component",{
			template: "#word_input_template",
			props: ["word"],
			methods:{
				getCategory : function(){
					var self = this;
					 jQuery.get("/search_category",{word: this.word.value},function(categories){
						 	self.word.categories = categories;
						 	self.word.selected_category = categories[0];							
					 });
				}
			}
		});
	
		new Vue({
			el: "#sentence_form",
			data: {
				words: [
					{value: "",selected_category: null,categories: []},
					{value: "",selected_category: null,categories: []},
					{value: "",selected_category: null,categories: []},
					{value: "",selected_category: null,categories: []},
					{value: "",selected_category: null,categories: []}                         
				]
			},
			methods:{
				addWord : function(){
					this.words.push({value: "",selected_category: null,categories: []});
				},
				removeWord: function(){
					this.words.pop();
				},
				submit: function(e){
					
					var submitedWords = [];
					for(var i=0; i < this.words.length; i++){
						var word = this.words[i];
						if(word.value !== "" && word.selected_category !== null){
							submitedWords.push({
								value: word.value,
								category: word.selected_category
							});
						}
					}
					console.log(this.words);
					console.log(submitedWords);
					
					jQuery.ajax({
						
						type: "POST",
						url: "/parse1",
						contentType: "application/json",
						data: JSON.stringify(submitedWords),
						success: function(charts){
							
							console.log(charts);
							$("#parse_chart").empty();
							for(var i=0 ; i < submitedWords.length+1; i++){
								var word  = "";
								if(i === 0){ word = "Root";  }
								else{ word = submitedWords[i-1].value;}
								var text = "<div id='app"+i+"' class='earley-col' >\
										<p>"+i+": "+word+"</p>\
									</div>";
								$("#parse_chart").append(text);
							}
							draw(charts);
						}
						
					});
				}
			}
		});

		var draw = function(charts){
				if(charts === null || charts === undefined || charts.length < 1){
					alert("Parse fail.");
					return;
				}
				var id = 0;
				for(var i =0; i < charts.length; i++){
					chart = charts[i].chart;
					for(var j=0; j < chart.length; j++){
						var state = chart[j];
						id ++;
						state.id = id;
						chart[j].print = false;
					}
				}
				var set = new Set();
				var stateToString = function(st){
					if(st === null || st === undefined){
						return "";
					}
					var temp = st.rhs.terms;
					var rhs = "";
					for (var z = 0; z < temp.length; z++) {
						rhs = rhs + temp[z] + " ";
					}
					return st.j + " " + st.lhs + "->" + rhs;
				};
				
				var displayState = function(st){
					var temp = st.rhs.terms;
					var rhs = "";
					for (var z = 0; z < temp.length; z++) {
						rhs = rhs + temp[z] + " ";
					}
					var html = "<p id='state_" +st.id+ "' class='active'>" + st.i + " " + st.lhs + "->" + rhs + "</p>";
					$("#app"+st.j).append(html);
					setTimeout(function(){
						$("#state_"+st.id).removeClass("active");
					},1000);
					console.log(st.i + " " + st.lhs + "->" + rhs)
				};
				
				var getGeneratedState = function(chart,i){
					var state  = chart[i];
					var afterDot = state.afterDot;
					var states = [];

					if(state.dotLast){
						var lhs = state.lhs;
						for(var k = i+1; k < chart.length; k++){
							if(chart[k].print === true){
								continue;
							}
							if(lhs === chart[k].priorToDot){
								chart[k].print = true;
								states.push(chart[k]);
							}else{
								break;
							}
						}					
					}else{
						if(set.has(afterDot)){
							return states;
						}else{
							set.add(afterDot);
						}
						for(var k = i+1; k < chart.length; k++){
							if(chart[k].print === true){
								continue;
							}
							if(afterDot === chart[k].lhs){
								chart[k].print = true;
								states.push(chart[k]);
							}else{
								break;
							}
						}
					}
					return states;
				}


				var getStateByAfterDot = function(chart,afterDot){
					var result = [];
					for (var i = chart.length - 1; i >= 0; i--) {
						if(chart[i].afterDot === afterDot){
							result.push(chart[i]);
						}
					}
					return result;
				};

				var display_elements = [];
				var chart = null;
				var state = null;
				
				for(var i =0; i < charts.length; i++){
					
					chart = charts[i].chart;
					set = new Set();
					display_elements.push({
						hightlight_els: null,
						displays: new Array(chart[0])
					});
					for(var j=0; j < chart.length; j++){
						var state = chart[j];
						var states = getGeneratedState(chart,j);
						var hightlight_els = null;
						if(!state.dotLast){
							hightlight_els = new Array(state);
						}else{
							hightlight_els = getStateByAfterDot(charts[state.i].chart,state.lhs);
							hightlight_els.push(state);
						}
						display_elements.push({
							hightlight_els: hightlight_els,
							displays: states 
						});
					}
				}


				setInterval(function(){
					
					if(display_elements.length > 0){
						var display_el = display_elements[0];
						display_elements.shift();
						var hightlight_els = display_el.hightlight_els;
						if(hightlight_els !== null){
							for(var u =0; u < hightlight_els.length; u++){
								var id = "#state_"+hightlight_els[u].id;
								$(id).css("background-color","grey");
								(function(id_param){
									setTimeout(function(){
										$(id_param).css("background-color","white");
									},1800);
								})(id);
							}
						}
						setTimeout(function(){
							for(var i=0; i < display_el.displays.length; i++){
								displayState(display_el.displays[i]);
							}
						},500)
					}
				},3000);

			};
			
		
		});