const base = {
    get() {
        return {
            url: "http://localhost:8080/ssm227gu/",
            name: "ssm227gu",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/ssm227gu/front/index.html'
        };
    },
    getProjectName() {
        return {
            projectName: "线上游戏周边商城"
        }
    }
}
export default base
