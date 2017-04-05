var gulp = require('gulp');
var gutil = require('gulp-util');
var bower = require('bower');
var concat = require('gulp-concat');
var cleanCSS = require('gulp-clean-css');
var sass = require('gulp-sass');
var rename = require('gulp-rename');
var sh = require('shelljs');
var del = require('del');
var notify = require('gulp-notify');
var plumber = require('gulp-plumber');
var sourcemaps = require('gulp-sourcemaps');

/* 设置路径 */
var paths = {
  css   : "./www/css/",
  js    : "./www/js/",
  scss  : "./scss/",
  img   : "./www/img/"
}

// gulp.task('default', ['sass']);

gulp.task('sass', function(done) {
  return gulp.src(paths.scss + 'ionic.app.scss')
    .pipe(plumber({errorHandler: notify.onError('Error: <%= error.message %>')}))
    .pipe(sass({ style: 'expanded' }))
    .pipe(rename('style_auto.css'))
    .pipe(sourcemaps.init())
    .pipe(cleanCSS())
    .pipe(sourcemaps.write('./'))
    .pipe(gulp.dest(paths.css))
    .pipe(notify({ message: 'sass compile complete' }));
});

// gulp.task('watch', function() {
//   gulp.watch(paths.sass, ['sass']);
// });

gulp.task('install', ['git-check'], function() {
  return bower.commands.install()
    .on('log', function(data) {
      gutil.log('bower', gutil.colors.cyan(data.id), data.message);
    });
});

gulp.task('git-check', function(done) {
  if (!sh.which('git')) {
    console.log(
      '  ' + gutil.colors.red('Git is not installed.'),
      '\n  Git, the version control system, is required to download Ionic.',
      '\n  Download git here:', gutil.colors.cyan('http://git-scm.com/downloads') + '.',
      '\n  Once git is installed, run \'' + gutil.colors.cyan('gulp install') + '\' again.'
    );
    process.exit(1);
  }
  done();
});

gulp.task('serve:before', ['sass','watch']);

// Clean
gulp.task('clean', function(cb) {
  del(paths.css, cb)
});
// Default task
gulp.task('default', ['clean'], function() {
  gulp.start('sass');
});

// Watch
gulp.task('watch', function() {
  // Watch .scss files
  gulp.watch(paths.scss + '/**/*.scss', ['sass']);
});
