// Load gulp
var gulp = require('gulp');

// Load plugins
var rename = require('gulp-rename');
var template = require('gulp-template');
var activeModule, htmlMsg;

htmlMsg = require('./views/html_msg.json');

// 英文
gulp.task('en_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.en_html_data))
        .pipe(rename("index.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 简体中文
gulp.task('hans_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.hans_html_data))
        .pipe(rename("index-hans.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 繁体
gulp.task('hant_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.hant_html_data))
        .pipe(rename("index-hant.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 日文
gulp.task('ja_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.ja_html_data))
        .pipe(rename("index-ja.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 法语
gulp.task('fr_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.fr_html_data))
        .pipe(rename("index-fr.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 西班牙语
gulp.task('es_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.es_html_data))
        .pipe(rename("index-es.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 克罗地亚语
gulp.task('hr_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.hr_html_data))
        .pipe(rename("index-hr.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 德语
gulp.task('de_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.de_html_data))
        .pipe(rename("index-de.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 俄
gulp.task('ru_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.ru_html_data))
        .pipe(rename("index-ru.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 韩
gulp.task('ko_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.ko_html_data))
        .pipe(rename("index-ko.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 意大利
gulp.task('it_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.it_html_data))
        .pipe(rename("index-it.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 波兰语
gulp.task('pl_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.pl_html_data))
        .pipe(rename("index-pl.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// 罗马尼亚
gulp.task('ro_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.ro_html_data))
        .pipe(rename("index-ro.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

// et
gulp.task('et_html', function () {
    return gulp.src('views/' + activeModule + '/default.html')
        .pipe(template(htmlMsg.et_html_data))
        .pipe(rename("index-et.html"))
        .pipe(gulp.dest('views/' + activeModule + '/'));
});

gulp.task('airblock', function () {
    activeModule = 'airblock';
    gulp.start('en_html', 'hans_html', 'hant_html', 'ja_html', 'fr_html', 'es_html', 'hr_html','de_html', 'ru_html','pl_html', 'ro_html', 'et_html');
});

gulp.task('makeblockhd', function () {
    activeModule = 'makeblockhd';
    gulp.start('en_html', 'hans_html', 'hant_html', 'ja_html', 'fr_html', 'es_html', 'hr_html','de_html', 'ru_html', 'it_html','ko_html','pl_html', 'ro_html', 'et_html');
});

gulp.task('makeblockNeuron', function () {
    activeModule = 'makeblockNeuron';
    gulp.start('en_html', 'hans_html', 'hant_html','de_html');
});

// Default task
gulp.task('default', function() {
    gulp.start('makeblockhd');
});

// all task
gulp.task('all', function() {
    gulp.start('makeblockhd', 'makeblockNeuron', 'airblock');
});
