import axios from "axios";

const INV_API_BASE_URL = 'http://localhost:8080/invitation'

class InvitationService{

    postInvitation(username){
        return axios.post(INV_API_BASE_URL+'/save',username);
    }

    getInvitations(){
        return axios.get(INV_API_BASE_URL+'/myInvitations');
    }

    acceptInvitation(invitationId){
        return axios.post(INV_API_BASE_URL+'/accept',invitationId);
    }

    declineInvitation(invitationId){
        return axios.post(INV_API_BASE_URL+'/decline',invitationId);
    }

    deleteInvitation(invitationId){
        return axios.delete(INV_API_BASE_URL+'/delete',invitationId);
    }
}

export default new InvitationService()
