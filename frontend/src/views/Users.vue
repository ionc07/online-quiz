<template>
  <div>
    <ConfirmationModal
        :display="displayUserDelete"
        :title="userFullNameToDelete"
        :message="'Do you really want to delete this user?'"
        @close="closeUserDeleteDialog"
        @confirm="deleteUser"
    />
    <v-data-table
        :headers="headers"
        :items="users"
        multi-sort
        hide-default-footer
        :items-per-page="pageSize"
        :page="page"
        :custom-sort="customSort"
        :sort-by="sortBy"
        :sort-desc="sortDesc"
        class="mt-8"
    >
      <template v-slot:item.enabled="{ item }">
        <v-icon>{{
            item.enabled ? "mdi-check-bold" : "mdi-close-thick"
          }}
        </v-icon>
      </template>
      <template v-slot:item.role="{ item }">
        <v-tooltip top>
          <template v-slot:activator="{ on, attrs }">
            <div v-bind="attrs" v-on="on" class="table-role-img">
              <img
                  width="130%"
                  v-show="item.role === 'ROLE_admin'"
                  src="../assets/admin_role.png"
              />
              <img
                  width="110%"
                  v-show="item.role === 'ROLE_editor'"
                  src="../assets/editor_role.png"
              />
              <img
                  width="105%"
                  v-show="item.role === 'ROLE_user'"
                  src="../assets/user_role.png"
              />
            </div>
          </template>
          <span>{{ item.role }}</span>
        </v-tooltip>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon class="mr-3 user-action" @click="openUserEditDialog(item.id)"
        >mdi-account-edit
        </v-icon
        >
        <v-icon
            class="user-action"
            @click="
            openUserDeleteDialog(item.id, item.firstName + ' ' + item.lastName)
          "
        >mdi-delete-forever
        </v-icon
        >
      </template>
    </v-data-table>
    <div class="text-center pagination inline">
      <v-pagination
          :value="page"
          :length="totalPages"
          :total-visible="7"
          @input="changePage"
      ></v-pagination>
    </div>
<!--    <div class="inline absolute">-->
<!--      <v-select-->
<!--          :items="pageSizes"-->
<!--          label="Rows"-->
<!--          dense-->
<!--          v-model="pageSize"-->
<!--          @change="changePageSize"-->
<!--      ></v-select>-->
<!--    </div>-->
  </div>
</template>
<script>
import api from "../components/backend-api";
import ConfirmationModal from "../components/modal/ConfirmationModal";

export default {
  components: {ConfirmationModal},
  data() {
    return {
      pageSizes: [10, 20, 50],
      pageSize: 10,
      sortBy: [],
      sortDesc: [],
      headers: [
        {text: "First name", value: "firstName", sortable: true},
        {text: "Last name", value: "lastName", sortable: true},
        {text: "Email", value: "email", sortable: true},
        {text: "Created", value: "createdAt", sortable: true},
        {text: "Role", value: "role", sortable: true},
        {text: "Actions", value: "actions", sortable: false}
      ],
      users: [],
      page: 1,
      totalPages: 0,
      sortParams: "",
      displayUserDelete: false,
      userIdToDelete: 0,
      userFullNameToDelete: ""
    };
  },
  methods: {
    changePageSize(pageSize) {
      this.pageSize = pageSize;
      localStorage.setItem("users_page_size", pageSize);
      this.page = 1;
      this.fetchUsers();
    },
    changePage(page) {
      if (page !== this.page) {
        this.page = page;
        this.fetchUsers();
      }
    },
    fetchUsers() {
      this.$store.state.app.fetchLoading = true;
      api
          .getUsers({
            page: this.page,
            size: this.pageSize,
            sortParams: this.sortParams
          })
          .then(r => {
            this.users = r.data.content;
            this.totalPages = r.data.totalPages;
            window.scrollTo({
              top: 0,
              behavior: "smooth"
            });
            this.$store.state.app.fetchLoading = false;
          });
    },
    customSort: function (items, columns, isDesc) {
      var customSortParams = "";
      for (let i = 0; i < columns.length; i++) {
        customSortParams +=
            "&sort=" + columns[i] + "," + (isDesc[i] ? "DESC" : "ASC");
      }
      if (customSortParams !== this.sortParams) {
        this.sortParams = customSortParams;
        this.fetchUsers();
      }

      return this.users;
    },
    openUserEditDialog() {
    },
    openUserDeleteDialog(userId, userFulName) {
      this.userIdToDelete = userId;
      this.userFullNameToDelete = userFulName;
      this.displayUserDelete = true;
    },
    closeUserDeleteDialog() {
      this.displayUserDelete = false;
    },
    deleteUser() {
      api.deleteUser(this.userIdToDelete).then(() => {
        this.displayUserDelete = false;
        this.fetchUsers();
      });
    }
  },
  beforeMount() {
    var storedPageSize = localStorage.getItem("users_page_size");

    if (storedPageSize !== null) {
      this.pageSize = parseInt(storedPageSize);
    }

    this.fetchUsers();
  }
};
</script>
<style lang="css">
.table-role-img {
  width: 20px;
}

.pagination {
  margin-top: 2rem;
  margin-bottom: 6rem;
  width: 100% !important;
}

.inline {
  display: inline-block !important;
}

.absolute {
  width: 7rem;
  position: absolute !important;
  right: 10% !important;
  margin-top: 2.4rem !important;
}

.theme--light.v-pagination .v-pagination__item {
  transition: 0s !important;
}

.theme--light.v-pagination .v-pagination__item--active {
  background-color: #001f3f !important;
  color: white;
}

.user-action:hover {
  cursor: pointer;
}
</style>