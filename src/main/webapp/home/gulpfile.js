var gulp = require("gulp");
var gulpLoadPlugins = require("gulp-load-plugins");
var plugins = gulpLoadPlugins();
var browserSync = require('browser-sync');
//var karma = require('gulp-karma');
var del = require("del");
////////////////////////////////////////////////////////////////////////////////////////////////////////////版本
//版本



var copyCssImagesFiles=[
    "./styles/images/*"
];
//压缩本地的js文件路径 build-local-js
var buildLocalFiles=[
    "./scripts/*.js"
];

//压缩本地的css文件路径 build-local-css
var buildLocalCss=[
    "./styles/*.css"
];

///////////////////////////////////////////////////////////////////////////////////////////////////////版本公共的js


// 图片处理
gulp.task('copy-css-images', function(){
    gulp.src(copyCssImagesFiles)
        .pipe(gulp.dest("./dist/styles/images/"));
});

//清除字font文件夹
gulp.task("clean-font", function(){
    gulp.src(["./dist/styles/font/*.ttf","./dist/styles/font/*.eot","./dist/styles/font/*.svg","./dist/styles/font/*.woff"], { read:false })
        .pipe(plugins.clean());
});

//清除css样式
gulp.task("clean-all-css", function(){
    gulp.src("./dist/styles/*.css",{ read:false })
        .pipe(plugins.clean());
});

//清除js样式
gulp.task("clean-all-js", function(){
    gulp.src("./dist/scripts/*.js", { read:false })
        .pipe(plugins.clean());
});

//清除css样式
gulp.task("clean-local-css", function(){
    gulp.src("./dist/**/local.min.css", { read:false })
        .pipe(plugins.clean());
});

//清除js样式
gulp.task("clean-local-js", function(){
    gulp.src("./dist/**/local.min.js", { read:false })
        .pipe(plugins.clean());
});

// css合并，压缩文件
gulp.task("build-local-less",["clean-local-css"], function() {
    gulp.src(buildLocalCss)
        .pipe(plugins.minifyCss())
        .pipe(plugins.rename("local.min.css"))
        .pipe(gulp.dest("./dist/styles/"));

});


gulp.task("build-local-js",["clean-local-js"],function() {
    gulp.src(buildLocalFiles)
        .pipe(plugins.concat("local.src.js"))
        .pipe(plugins.uglify())
        .pipe(plugins.rename("local.min.js"))
        .pipe(gulp.dest("./dist/scripts/"));
});

gulp.task("watch", function() {
    gulp.watch("styles/*", ["build-local-less",browserSync.reload]);
    gulp.watch("scripts/**/*.js", ["build-local-js",browserSync.reload]);
    gulp.watch("styles/images/", ["copy-css-images",browserSync.reload]);
    gulp.watch("templates/**/*.vm", ["bs-reload"]);
});

//检查错误代码
gulp.task('lint', function() {
    gulp.src('./scripts/*.js')
        .pipe(plugins.jshint())
        .pipe(plugins.jshint.reporter('default'));
});

//浏览器同步
gulp.task('browser-sync', function() {
    browserSync({
        proxy: "http://localhost:80"
    });
});

// 定义develop任务在日常开发中使用
gulp.task("clean-all",["clean-all-css","clean-all-js","clean-font"],function(){});

// 定义develop任务在日常开发中使用
gulp.task("dev",["build-local-js","build-local-less","copy-css-images"],function(){});

// Reload all Browsers
gulp.task('bs-reload', function () {
    browserSync.reload();
});

// gulp命令默认启动的就是default认为,这里将clean任务作为依赖,也就是先执行一次clean任务,流程再继续.
gulp.task("default",["browser-sync","clean-all","dev"], function() {
    gulp.run("watch");
});
