module.exports = function(grunt){
grunt.initConfig({
pkg: grunt.file.readJSON('package.json'),
/*uglify:{
	build:{
		src:'jquery-2.1.1.js',
		dest:'jquery-2.1.1.min.js'
	}
},*/

eslint: {
		target:['./*'],
		options:{
			useEslintrc:true
		}
	}	
});

grunt.loadNpmTasks('grunt-contrib-uglify');
grunt.loadNpmTasks('grunt-eslint');
grunt.registerTask('default',['eslint']);


};