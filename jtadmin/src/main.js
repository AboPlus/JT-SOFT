/*
* 个人总结：
        1.import...from...的from命令后面可以跟很多路径格式，若只给出vue，axios这样的包名，
            则会自动到node_modules中加载；若给出相对路径及文件前缀，则到指定位置寻找。
        2.可以加载各种各样的文件：.js、.vue、.less等等。
        3.可以省略掉from直接引入。
* */
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/ali-icon/iconfont.css'
/* 导入富文本编辑器 */
import VueQuillEditor from 'vue-quill-editor'

/* 导入富文本编辑器对应的样式 */
import 'quill/dist/quill.core.css' // import styles
import 'quill/dist/quill.snow.css' // for snow theme
import 'quill/dist/quill.bubble.css' // for bubble theme

/* 导入axios包 */
import axios from 'axios'
/* 设定axios的请求根目录 */
axios.defaults.baseURL = 'http://localhost:8091/'
/* 向vue对象中添加全局对象 以后发送ajax请求使用$http对象 */
Vue.prototype.$http = axios

Vue.config.productionTip = false

/* 定义过滤器 */
Vue.filter("priceFormat",function(price){

    return (price / 100).toFixed(2)
})

/* 将富文本编辑器注册为全局可用的组件 */
Vue.use(VueQuillEditor)


new Vue({
  // Vue对象引入路由机制，
  router,
  // 指定位置渲染的动作 App.vue中进行渲染
    /*
    * render函数是vue通过js渲染dom结构的函数createElement，约定可以简写为h
    * 即h就是createElement函数
    * $mount(’#app’) ：手动挂载到id为app的dom中的意思
    * */
  render: h => h(App)
}).$mount('#app')
