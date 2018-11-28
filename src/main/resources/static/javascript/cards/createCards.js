var parent = document.getElementById('formulaire');
var form = document.getElementById('insertForm');
var formWeapon = document.getElementById('insertFormWeapon');

function reset() {
    document.getElementsByClassName('bonus1').innerText="";
    document.getElementsByClassName('bonus2').innerText="";
    document.getElementsByClassName('leftbot').innerText="";
    document.getElementsByClassName('rightbot').innerText="";
}

function printCost(c) {
    var a = document.getElementsByClassName('rightbot');
    (c > 0) ? a.innerText=c+' pièces d\'or' : a.innerText="";
}

function insertMonsterForm() {
    reset();
    form.insertAdjacentHTML('beforeend',
        '<label for="lvl">Niveau :</label> <input type="number" name="level" id="lvl" min="1" max="99" onkeyup="bonus1.innerText=this.value;bonus2.innerText=this.value"> <label for="treasure">Trésor(s) gagné(s) :</label> <input type="number" name="treasure" id="treasure" min="1" max="9" onkeyup="this.value>1?rightbot.innerText=this.value+\' trésors\':rightbot.innerText=this.value+\' trésor\';"> <label for="leak">Minimum fuite :</label> <select name="leak" id="leak"> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option><option value="4">4</option> <option value="5" selected>5</option> </select> <label for="malus">Action si fuite raté :</label> <select name="malus" id="malus"> <option value="">Effet 1</option> <option value="">Effet 2</option> </select>'
    );
    document.getElementById('name').setAttribute('name', "monster.name");
    document.getElementById("desc").setAttribute("name", "monster.description");
    document.getElementById('img').setAttribute('name', "monster.image");
}

function insertCurseForm() {
    reset();
    form.insertAdjacentHTML('beforeend',
        '<label for="curse">Effet malédiction :</label> <select name="curse" id="curse"> <option value="">Malédiction 1</option> <option value="">Malédiction 2</option> </select>'
    );
    document.getElementById('name').setAttribute('name', "curse.name");
    document.getElementById("desc").setAttribute("name", "curse.description");
    document.getElementById('img').setAttribute('name', "curse.image");
}

function insertEquipmentForm() {
    reset();
    form.insertAdjacentHTML('beforeend',
        '<label for="att">Point d\'attaque :</label> <input type="number" name="attack" id="att" min="0" max="9" onkeyup="bonus1.innerText=\'+\'+this.value;bonus2.innerText=\'+\'+this.value"> <label id="cost">Valeur pièce d\'or :</label> <input type="number" name="cost" id="cost" min="0" max="2000" onkeyup="printCost(this.value)"> <label for="type_equip">Type d\'équipement :</label> <select name="type_equip" id="type_equip" onChange="addEquipType()"> <option value="head">Couvre-chef</option> <option value="armor">Armure</option> <option value="boot">Bottes</option> <option value="weapon">Arme</option> <option value="other">Autres</option> </select> <label>Poids :</label> <input type="radio" name="weight" value="big" id="big"> <label for="big" class="radio">Gros</label> <input type="radio" name="weight" value="normal" id="normal" checked> <label for="normal" class="radio">Normal</label>'
    );
}

function insertWeaponForm() {
    reset();
    formWeapon.insertAdjacentHTML('beforeend',
        '<label>Nombres de main :</label> <input type="radio" name="hand" value="1" id="1hand" checked> <label for="1hand" class="radio">1 main</label> <input type="radio" name="hand" value="2" id="2hand"> <label for="2hand" class="radio">2 mains</label>'
    );
    document.getElementById('name').setAttribute('name', "weapon.name");
    document.getElementById("desc").setAttribute("name", "weapon.description");
    document.getElementById('img').setAttribute('name', "weapon.imgage");
}

addForm();

function addForm() {
    form.innerHTML = "";
    formWeapon.innerHTML = "";
    switch (parent.type.selectedIndex) {
        case 0:
            insertEquipmentForm();
            break;
        case 1:
            insertMonsterForm();
            break;
        case 2:
            insertCurseForm();
            break;
        default:
            break;
    }
}

function selectForm(dtype) {
    form.innerHTML="";
    formWeapon.innerHTML="";
    var select = document.getElementById('select_type');
    if (dtype === "monster") {
        select.selectedIndex=1;
        insertMonsterForm();
    } else if (dtype === "curse") {
        select.selectedIndex=2;
        insertCurseForm();
    } else if (dtype === "weapon") {
        select.selectedIndex=0;
        insertEquipmentForm();
        insertWeaponForm();
        document.getElementById('type_equip').selectedIndex=3
    } else if (dtype === "boots") {
        select.selectedIndex=0;
        insertEquipmentForm();
        document.getElementById('type_equip').selectedIndex=2
    } else if (dtype === "head") {
        select.selectedIndex=0;
        insertEquipmentForm();
        document.getElementById('type_equip').selectedIndex=0
    } else if (dtype === "armor") {
        select.selectedIndex=0;
        insertEquipmentForm();
        document.getElementById('type_equip').selectedIndex=1
    } else if (dtype === "other") {
        select.selectedIndex=0;
        insertEquipmentForm();
        document.getElementById('type_equip').selectedIndex=4
    }
}

function addEquipType() {
    formWeapon.innerHTML = "";
    switch (parent.type_equip.selectedIndex) {
        case 0:
            document.getElementById('name').setAttribute('name', "head.name");
            document.getElementById("desc").setAttribute("name", "head.description");
            document.getElementById('img').setAttribute('name', "head.image");
            document.getElementById('att').setAttribute('name', "head.attack");
            document.getElementById('cost').setAttribute('name', "head.cost");
            document.getElementById('big').setAttribute('name', "head.bigItem");
            break;
        case 1:
            document.getElementById('name').setAttribute('name', "armor.name");
            document.getElementById("desc").setAttribute("name", "armor.description");
            document.getElementById('img').setAttribute('name', "armor.image");
            document.getElementById('att').setAttribute('name', "armor.attack");
            document.getElementById('cost').setAttribute('name', "armor.cost");
            document.getElementById('big').setAttribute('name', "armor.bigItem");
            break;
        case 2:
            document.getElementById('name').setAttribute('name', "boots.name");
            document.getElementById("desc").setAttribute("name", "boots.description");
            document.getElementById('img').setAttribute('name', "boots.image");
            document.getElementById('att').setAttribute('name', "boots.attack");
            document.getElementById('cost').setAttribute('name', "boots.cost");
            document.getElementById('big').setAttribute('name', "boots.bigItem");
            break;
        case 3:
            document.getElementById('name').setAttribute('name', "weapon.name");
            document.getElementById("desc").setAttribute("name", "weapon.description");
            document.getElementById('img').setAttribute('name', "weapon.image");
            document.getElementById('att').setAttribute('name', "weapon.attack");
            document.getElementById('cost').setAttribute('name', "weapon.cost");
            document.getElementById('big').setAttribute('name', "weapon.bigItem");
            insertWeaponForm();
            break;
        case 4:
            document.getElementById('name').setAttribute('name', "other.name");
            document.getElementById("desc").setAttribute("name", "other.description");
            document.getElementById('img').setAttribute('name', "other.image");
            document.getElementById('att').setAttribute('name', "other.attack");
            document.getElementById('cost').setAttribute('name', "other.cost");
            document.getElementById('big').setAttribute('name', "other.bigItem");
            break;
    }
}