import Vue from "vue";
import VueRouter from "vue-router";
import UsersView from "./views/Users.vue";
import DashBoardView from "./views/Dashboard.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import store from './store'
import Home from "@/views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/createTest",
    name: "Create test",
    meta: {requiresAuth: true},
    component: () => import("./views/CreateTest.vue")
  },
  {
    path: "/tests",
    name: "UserTests",
    meta: {requiresAuth: true},
    component: () => import("./views/UserTests.vue")
  },
  {
    path: "/userGroups",
    name: "User groups",
    meta: {requiresAuth: true},
    component: () => import("./views/UserGroups.vue")
  },
  {
    path: "/users",
    name: "Users",
    component: UsersView,
    meta: {
      requiresAuth: true,
      role: "ROLE_admin"
    }
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: DashBoardView,
    meta: {requiresAuth: true, role: "ROLE_admin"}
  },
  {
    path: "/login", component: Login,
    name: "Login",
    meta: {
      requiresGuest: true,
    }
  },
  {
    path: "/register", component: Register,
    name: "Register",
    meta: {
      requiresGuest: true,
    }
  },
  {
    path: "/account", component: Home,
    name: "Account",
    meta: {
      requiresAuth: true,
    }
  },
  // otherwise redirect to tests
  {path: "*", redirect: "/tests"}
];

const router = new VueRouter({
  routes,
  mode: "history"
});

router.beforeEach((to, from, next) => {
  const isLoggedIn = store.state.auth.status.loggedIn;
  console.log(store.state)
  console.log(`isLoggedIn[${isLoggedIn}]`);
  // Allow access to Login and Register pages if user is not logged in
  if (!isLoggedIn && (to.name === 'Login' || to.name === 'Register')) {
    return next();
  }

  // Redirect to Tests page if user is logged in and trying to access Login or Register pages
  if (isLoggedIn && (to.name === 'Login' || to.name === 'Register')) {
    return next('/tests');
  }

  // For other routes, ensure authentication if required
  if (to.meta.requiresAuth && !isLoggedIn) {
    return next('/login');
  }

  next();
});

export default router;
