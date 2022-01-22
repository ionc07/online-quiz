import {shallowMount} from "@vue/test-utils";
import NavBar from "@/components/NavBar";

describe("NavBar.vue", () => {
  it("should return 3 admin nav links if admin property is true", () => {
    const wrapper = shallowMount(NavBar, {
      mocks: {
        $vuetify: {breakpoint: {}},
        $store: {state: {admin: true, app: {activeRoute: "Page name"}}}
      }
    });
    const navLinks = wrapper.vm.getNavLinks();
    expect(navLinks.length).toBe(3);
  });
});

describe("NavBar.vue", () => {
  it("should return 4 user nav links if admin property is false", () => {
    const wrapper = shallowMount(NavBar, {
      mocks: {
        $vuetify: {breakpoint: {}},
        $store: {state: {admin: false, app: {activeRoute: "Page name"}}}
      }
    });
    const navLinks = wrapper.vm.getNavLinks();
    expect(navLinks.length).toBe(4);
  });
});
