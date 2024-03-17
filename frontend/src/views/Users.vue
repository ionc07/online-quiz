<template>
  <div class="mt-12">
    <v-card
        elevation="4" class="elevator">
      <ConfirmationModal
          :display="displayUserDelete"
          :title="userFullNameToDelete"
          :message="'Do you really want to delete this user?'"
      />
      <v-data-table
          :headers="headers"
          :items="users"
          multi-sort
          hide-default-footer
          :items-per-page="pageSize"
          :page="page"
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
              <div v-bind="attrs" v-on="on" class="role">
                <span v-if="item.role === 'ROLE_admin'" class="admin-role">Admin</span>
                <span v-if="item.role === 'ROLE_user'" class="user-role">User</span>
              </div>
            </template>
            <span>{{ item.role }}</span>
          </v-tooltip>
        </template>
        <template v-slot:item.actions="{ item }">
          {{item.role}}
          <v-icon class="mr-3 user-action">mdi-account-edit</v-icon>
          <v-icon class="user-action">mdi-delete-forever</v-icon>
        </template>
      </v-data-table>
      <div class="text-center pagination inline">
        <v-pagination
            :value="page"
            :length="totalPages"
            :total-visible="7"
        ></v-pagination>
      </div>
      <div class="inline absolute">
        <v-select
            :items="pageSizes"
            label="Rows"
            dense
            v-model="pageSize"
        ></v-select>
      </div>
      <div>
      </div>
    </v-card>
  </div>

</template>
<script>
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
  methods: {}
};
</script>
<style lang="css">
/*.table-role-img {*/
/*  width: 20px;*/
/*}*/

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

.user-role {
  width: 20px;
  color: #035203;
}

.admin-role {
  color: #690101;
}

.elevator {
  margin: 90px 10px 10px 10px;
}
</style>