import {shallowMount} from "@vue/test-utils";
import Authentication from "@/views/Authentication";

describe("Authentication.vue", () => {
  it("should be set by default actionType Login", async () => {
    const wrapper = shallowMount(Authentication, {
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

    expect(wrapper.vm.$data.actionType).toBe("Login");
  });
});
