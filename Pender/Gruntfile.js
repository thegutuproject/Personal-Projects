module.exports = function(grunt) {
  grunt.initConfig({
    pgk : grunt.file.readJSON('package.json'),

    uglify: {
      build: {
        files: {
          'dist/js/app.min.js': ['src/js/**/*.js', 'src/js/*.js']
        }
      }
    },

    jshint: {
    // define the files to lint
      all: ['src/js/**/*.js'],
      // configure JSHint (documented at http://www.jshint.com/docs/)
      options: {
        // more options here if you want to override JSHint defaults
        globals: {
          jQuery: true,
          console: true,
          module: true
        }
      }
    },

    // COMMENTED OUT - NOT USE
    // process the less file to style.css
    less: {
      build: {
        files: {
          'dist/css/style.css': 'src/css/style.less'
        }
      }
    },

    cssmin: {
      build: {
        files: {
          'dist/css/style.min.css': 'src/css/**/*.css'
        }
      }
    },

    connect: {
      server: {
        options: {
          port: 8282,
          open: true
        }
      }
    },

    watch: {
      options: {
        livereload: 9002,
      },
      css: {
        files: ['src/css/**/*.less', 'src/css/**/*.css'],
        tasks: ['less', 'cssmin'],
      },
      js: {
        files: ['src/js/**/*.js'],
        tasks: ['jshint', 'uglify'],
      },
      html: {
        files: ['index.html'],
      },
      configFiles: {
        files: ['Gruntfile.js'],
        options: {
          reload: true
        }
      }
    }

  });

  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-connect');
  grunt.loadNpmTasks('grunt-contrib-watch');


  grunt.registerTask('default', ['cssmin', 'jshint', 'uglify', 'connect:server', 'watch']);
};
