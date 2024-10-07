console.log("contacts.js");

const viewContactModal = document.getElementById('view_contact_modal');

const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal () {

    contactModal.show();
}

function closeContactModal () {

    contactModal.hide();
}

async function loadContactData(id) {

    console.log(id);

    try {
        
        const data = await (await fetch(`http://localhost:8080/api/contact/${id}`)).json();
        console.log(data);

        document.querySelector('#contact_name').innerHTML = data.name;
        document.querySelector('#contact_email').innerHTML = data.email;
        document.querySelector('#contact_picture').src = data.picture;
        document.querySelector('#contact_number').innerHTML = data.phoneNumber;
        
        document.querySelector('#linkedin_link').href = data.linkedinLink;
        document.querySelector('#website_link').href = data.websiteLink;

        openContactModal();

    } catch (error) {
        
        console.error();
        
    }


}