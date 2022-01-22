import Vue from "vue";
import VueRouter from "vue-router";
import store from '../store'

Vue.use(VueRouter);

const routes = [
  {
    path: "/create",
    name: "CreateTest",
    component: () =>
        import("../views/CreateTest.vue"),
  },
  {
    path: "/tests",
    name: "UserTests",
    component: () =>
        import("../views/UserTests.vue"),
  },
  {
    path: "/userGroups",
    name: "UserGroups",
    component: () =>
        import("../views/UserGroups.vue"),
  },
  {
    path: "/users",
    name: "Users",
    component: () =>
        import("../views/Users.vue"),
    meta: {role: "ROLE_admin"},
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: () =>
        import("../views/Dashboard.vue"),
    meta: {role: "ROLE_admin"},
  },
  // otherwise redirect to tests
  {path: '*', redirect: '/tests'}
];

const router = new VueRouter({
  routes,
  mode: "history",
});


router.beforeEach((to, from, next) => {
  // if (to.matched.some(record => record.meta.requiresAuth)) {
  // this route requires auth, check if logged in
  // if not, redirect to login page.
  console.log(localStorage.getItem("role"));
  console.log(to.meta);

  if (localStorage.getItem("access_token") == null || localStorage.getItem("role") == null) {
    store.state.app.auth.loggedIn = false;
    next({
      path: "/login"
    });
  }

  next(); // make sure to always call next()!
});
export default router;
