import {shallowMount} from "@vue/test-utils";
import Login from "@/views/Login.vue";

describe("Login.vue", () => {
  it("should render an enabled login button", async () => {
    const wrapper = shallowMount(Login, {
      mocks: {
        $vuetify: {breakpoint: {}},
        $store: {state: {admin: true, app: {activeRoute: "Page name"}}}
      }
    });
    const loginButton = wrapper.find('.login-btn');
    const buttonExists = loginButton.exists();

    expect(buttonExists).toBe(true);
    expect(loginButton.classes("v-btn--disabled")).toBe(false);
  });
});


describe("Login.vue", () => {
  it("should emit switchActionType with value 'Login'", async () => {
    const wrapper = shallowMount(Login, {
      mocks: {
        $vuetify: {breakpoint: {}},
        $store: {
          state: {
            admin: false,
            app: {
              activeRoute: "", fetchLoading: false,
              auth: {loggedIn: false}
            },
          }
        }
      }
    });

    wrapper.find('#create-account').trigger('click');
    wrapper.vm.$nextTick(() => {
      wrapper.vm.goToRegister()
      expect(wrapper.emitted().switchActionType[0]).toEqual(["SignUp"]); //test if it changes
    })
  });
});