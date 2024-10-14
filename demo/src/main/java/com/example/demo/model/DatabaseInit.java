package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.example.demo.repositorio.AdministradorRepository;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.EspecialidadRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.ExcelService;

import org.springframework.boot.ApplicationArguments;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner {

        @Autowired
        ClienteRepository clienteRepository;
        @Autowired
        MascotaRepository mascotaRepository;
        @Autowired
        VeterinarioRepository veterinarioRepository;
        @Autowired
        EspecialidadRepository especialidadRepository;
        @Autowired
        TratamientoRepository tratamientoRepository;
        @Autowired
        DrogaRepository drogaRepository;
        @Autowired
        ExcelService excelService;
        @Autowired
        AdministradorRepository administradorRepository;

        @Override
        public void run(ApplicationArguments args) throws Exception {

                // Añade registros de clientes

                clienteRepository.save(new Cliente("Juan Carlos", "1435466", "juancarlos@gmail.com", "2343544354",
                                "Cll 79 # 13-21",
                                "https://img.a.transfermarkt.technology/portrait/big/94540-1636851420.jpg?lm=1"));
                clienteRepository.save(new Cliente("Pedro", "14789806", "pedro@gmail.com", "4366854", "Cll 80 # 89-21",
                                "https://ntvb.tmsimg.com/assets/assets/494807_v9_bd.jpg?w=360&h=480"));
                clienteRepository.save(new Cliente("Carlos Luis", "2234466", "carlos@gmail.com", "546544354",
                                "Cr 9 # 130-21",
                                "https://static.wikia.nocookie.net/esstarwars/images/2/29/Harrisonford.jpg/revision/latest?cb=20131222030038"));
                clienteRepository.save(new Cliente("Luis Alberto", "14789808", "luis@gmail.com", "56766854",
                                "Cll 79 # 13-21",
                                "https://img.peliplat.com/api/resize/v1?imagePath=std/202301/a/2/a216e91526720344073201406fb3bee0.jpg&mode=FILL&width=304&height=456&limit=false"));
                clienteRepository.save(new Cliente("Ana María", "1234567", "anamaria@gmail.com", "3001234567",
                                "Cll 9 #3-2",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Ana_de_Armas_GQ_2018_2.png/330px-Ana_de_Armas_GQ_2018_2.png"));
                clienteRepository.save(new Cliente("Miguel Ángel", "2345678", "miguelangel@gmail.com", "3002345678",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/Snoop_Dogg_2019_by_Glenn_Francis.jpg/330px-Snoop_Dogg_2019_by_Glenn_Francis.jpg"));
                clienteRepository.save(new Cliente("Laura Gómez", "3456789", "lauragomez@gmail.com", "3003456789",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Laura_Dern_Deauville_2017.jpg/330px-Laura_Dern_Deauville_2017.jpg"));
                clienteRepository.save(new Cliente("Sofía Martínez", "4567890", "sofia@gmail.com", "3004567890",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Sofía_Vergara_in_2020.jpg/330px-Sofía_Vergara_in_2020.jpg"));
                clienteRepository.save(new Cliente("Javier Pérez", "5678901", "javierperez@gmail.com", "3005678901",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/David_Beckham_UNICEF_%28cropped%29.jpg/263px-David_Beckham_UNICEF_%28cropped%29.jpg"));
                clienteRepository.save(new Cliente("Valentina López", "6789012", "valentina@gmail.com", "3006789012",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Serena_Williams_at_2013_US_Open.jpg/368px-Serena_Williams_at_2013_US_Open.jpg"));
                clienteRepository.save(new Cliente("Daniel Rodríguez", "7890123", "danielr@gmail.com", "3007890123",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/James_al_natural_-_Series%2C_01.jpg/338px-James_al_natural_-_Series%2C_01.jpg"));
                clienteRepository.save(new Cliente("Isabella Ramírez", "8901234", "isabella@gmail.com", "3008901234",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/4/41/Let_It_Snow_cast_vlogbrothers_1_%28cropped%29.png"));
                clienteRepository.save(new Cliente("Andrés Morales", "1234568", "andres@gmail.com", "3001234568",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/ChrisEvans2023.jpg/330px-ChrisEvans2023.jpg"));
                clienteRepository.save(new Cliente("Sara González", "2345679", "sara@gmail.com", "3002345679",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Sarah_Paulson_77th_Tony_Awards_2024.jpg/330px-Sarah_Paulson_77th_Tony_Awards_2024.jpg"));
                clienteRepository.save(new Cliente("Luis Miguel", "3456780", "luismiguel@gmail.com", "3003456780",
                                "Cll 79 # 13-21",
                                "https://indiehoy.com/wp-content/uploads/2023/09/paul-mccartney-foto.jpg"));
                clienteRepository.save(new Cliente("Natalia Vargas", "4567891", "natalia@gmail.com", "3004567891",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Natalie_Portman_2023.jpg/330px-Natalie_Portman_2023.jpg"));
                clienteRepository.save(new Cliente("Camilo Hernández", "5678902", "camilo@gmail.com", "3005678902",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Denzel_Washington_2018.jpg/375px-Denzel_Washington_2018.jpg"));
                clienteRepository.save(new Cliente("Marta Castaño", "6789013", "marta@gmail.com", "3006789013",
                                "Cll 79 # 13-21",
                                "https://www.rockandpop.cl/wp-content/uploads/2022/01/357a028ac38c548a047fc82906d27d5c.jpg"));
                clienteRepository.save(new Cliente("Oscar Jiménez", "7890124", "oscar@gmail.com", "3007890124",
                                "Cll 79 # 13-21",
                                "https://pics.filmaffinity.com/samuel_l_jackson-281405475762864-nm_200.jpg"));
                clienteRepository.save(new Cliente("Juliana Díaz", "8901235", "juliana@gmail.com", "3008901235",
                                "Cll 79 # 13-21",
                                "https://pics.filmaffinity.com/julianne_moore-129461114251768-nm_200.jpg"));
                clienteRepository.save(new Cliente("Alejandro Ruiz", "9012346", "alejandro@gmail.com", "3009012346",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Robert_De_Niro_Cannes_2016.jpg/330px-Robert_De_Niro_Cannes_2016.jpg"));
                clienteRepository.save(new Cliente("Claudia Moreno", "0123457", "claudia@gmail.com", "3000123457",
                                "Cll 79 # 13-21",
                                "https://i.pinimg.com/1200x/3e/37/b6/3e37b650cfa907d6267a70203b68fdfe.jpg"));
                clienteRepository.save(new Cliente("Héctor Castro", "1234569", "hector@gmail.com", "3001234569",
                                "Cll 79 # 13-21",
                                "https://www.angelfire.com/ny/conexion/lavoe_hector.jpeg"));
                clienteRepository.save(new Cliente("Lina Torres", "2345680", "lina@gmail.com", "3002345680",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Emma_Watson_2013.jpg/300px-Emma_Watson_2013.jpg"));
                clienteRepository.save(new Cliente("Santiago Pineda", "3456791", "santiago@gmail.com", "3003456791",
                                "Cll 79 # 13-21",
                                "https://www.quesomecanico.com/web/media/bearleague/thumb/bl16406488131020.png"));
                clienteRepository.save(new Cliente("Melissa Morales", "4567802", "melissa@gmail.com", "3004567802",
                                "Cll 79 # 13-21",
                                "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/1F0fCPNhb5W0WyFe8Tszfbx1DEp.jpg"));
                clienteRepository.save(new Cliente("Felipe Cortés", "5678913", "felipec@gmail.com", "3005678913",
                                "Cll 79 # 13-21",
                                "https://i.pinimg.com/564x/10/2a/fe/102afe8b7ac03cb144244226c77f56a8.jpg"));
                clienteRepository.save(new Cliente("Andrea Ruiz", "6789024", "andrea@gmail.com", "3006789024",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Arianna_Huffington_2011_Shankbone_2.JPG/597px-Arianna_Huffington_2011_Shankbone_2.JPG"));
                clienteRepository.save(new Cliente("María Fernanda", "8901246", "mariaf@gmail.com", "3008901246",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/María_Becerra_2023_03.jpg/330px-María_Becerra_2023_03.jpg"));
                clienteRepository.save(new Cliente("David Quintero", "9012357", "david@gmail.com", "3009012357",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/María_Becerra_2023_03.jpg/330px-María_Becerra_2023_03.jpg"));
                clienteRepository.save(new Cliente("Laura Carolina", "0123468", "lauracarolina@gmail.com", "3000123468",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Meryl_Streep_December_2018.jpg/330px-Meryl_Streep_December_2018.jpg"));
                clienteRepository.save(new Cliente("Carlos Martínez", "1234570", "carlosm@gmail.com", "3001234570",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/3/34/Charlie_Chaplin_portrait.jpg"));
                clienteRepository.save(new Cliente("Paola Hernández", "2345681", "paola@gmail.com", "3002345681",
                                "Cll 79 # 13-21",
                                "https://www.hola.com/imagenes/belleza/actualidad/20190306138482/penelope-cruz-joven-trucos-maquillaje-pelo/0-655-413/penelope-cruz-belleza-chanel-a.jpg"));
                clienteRepository.save(new Cliente("Jorge Salgado", "3456792", "jorge@gmail.com", "3003456792",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Michael_Jordan_in_2014.jpg/330px-Michael_Jordan_in_2014.jpg"));
                clienteRepository.save(new Cliente("Laura Vásquez", "4567803", "laurav@gmail.com", "3004567803",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Laura_Bozzo_2014.png/330px-Laura_Bozzo_2014.png"));
                clienteRepository.save(new Cliente("Mateo Ramírez", "5678914", "mateo@gmail.com", "3005678914",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jp"));
                clienteRepository.save(new Cliente("Ricardo Álvarez", "7890136", "ricardo@gmail.com", "3007890136",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Eminem_2021_Color_Corrected.jpg/800px-Eminem_2021_Color_Corrected.jpg"));
                clienteRepository.save(new Cliente("Tatiana Moreno", "8901247", "tatiana@gmail.com", "3008901247",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/9/97/Tania_Raymonde_2017_%28xwaKtuOkhX8%29.jpg"));
                clienteRepository.save(new Cliente("Santiago Pérez", "123456", "santiago@gmail.com", "3009012358",
                                "Cll 79 # 13-21",
                                "https://s3.abcstatics.com/Media/201404/05/cobain-foto--644x600.jpg"));
                clienteRepository.save(new Cliente("Daniela Ríos", "0123469", "daniela@gmail.com", "3000123469",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Angelina-Jolie_cropped.jpg/800px-Angelina-Jolie_cropped.jpg"));
                clienteRepository.save(new Cliente("Cristian Ortiz", "1234571", "cristian@gmail.com", "3001234571",
                                "Cll 79 # 13-21",
                                "https://images.ladepeche.fr/api/v1/images/view/5c1caedb3e454646793fbf72/full/image.jpg"));
                clienteRepository.save(new Cliente("Mónica Silva", "2345682", "monica@gmail.com", "3002345682",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Monica_Bellucci_2006.jpg/330px-Monica_Bellucci_2006.jpg"));
                clienteRepository.save(new Cliente("Gustavo Díaz", "3456793", "gustavo@gmail.com", "3003456793",
                                "Cll 79 # 13-21",
                                "https://www.fundacionkonex.org/custom/web/data/imagenes/repositorio/2010/6/1/1315/201603161112507f53f8c6c730af6aeb52e66eb74d8507.jpg"));
                clienteRepository.save(new Cliente("Laura Jiménez", "4567804", "lauraj@gmail.com", "3004567804",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Mary_Elizabeth_Winstead_by_Gage_Skidmore.jpg/330px-Mary_Elizabeth_Winstead_by_Gage_Skidmore.jpg"));
                clienteRepository.save(new Cliente("David Fernández", "5678915", "davidf@gmail.com", "3005678915",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/David_Ospina%2C_2015-05-31_%28cropped%29.JPG/330px-David_Ospina%2C_2015-05-31_%28cropped%29.JPG"));
                clienteRepository.save(new Cliente("Valeria Romero", "6789026", "valeria@gmail.com", "3006789026",
                                "Cll 79 # 13-21",
                                "https://es.web.img2.acsta.net/c_310_420/pictures/20/06/30/18/30/5671237.jpg"));
                clienteRepository.save(new Cliente("Emanuel Calderón", "7890137", "emanuel@gmail.com", "3007890137",
                                "Cll 79 # 13-21",
                                "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/kOJelnLSb89SeivbOCt1l94Hz2d.jpg"));
                clienteRepository.save(new Cliente("Natalia Martínez", "8901248", "natalia.martinez@gmail.com",
                                "3008901248", "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Gal_Gadot_2018_cropped_retouched.jpg/330px-Gal_Gadot_2018_cropped_retouched.jpg"));
                clienteRepository.save(new Cliente("Manuel Martínez", "9012359", "manuel.martinez@gmail.com",
                                "3009012359", "Cll 79 # 13-21",
                                "https://es.web.img3.acsta.net/c_310_420/pictures/15/11/20/15/12/197269.jpg"));
                clienteRepository.save(new Cliente("Elena Torres", "9876543", "elena.torres@gmail.com", "3009876543",
                                "Cll 50 # 12-34",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Emma_Stone_at_the_2018_Cannes_Film_Festival.jpg/330px-Emma_Stone_at_the_2018_Cannes_Film_Festival.jpg"));
                
                // agregar mascotas
                mascotaRepository.save(new Mascota("Toby", "5", "4", "Enfermo", "Leucemia felina",  "Macho", "Birmano",
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Manchas", "3", "6", "Enfermo", "Infección urinaria", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Orion", "4", "5", "Enfermo", "Problemas dentales", "Macho", "Burmés",
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Linsy", "5", "4.5", "Enfermo", "Enfermedad periodontal", "Hembra", "Ragdoll",
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Trixy", "3", "6", "Enfermo", "Infección de piel", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Nina", "4", "5", "Enfermo", "Infección urinaria", "Hembra", "Birmano",
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Rex", "3", "5.5", "Enfermo", "Problemas de piel", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Luna", "4", "4", "Enfermo", "Enfermedad respiratoria", "Hembra", "Burmés",
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Tom", "6", "5", "Enfermo", "Obesidad", "Macho", "Ragdoll",
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Bella", "3", "5.5", "Enfermo", "Alergia alimentaria", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
        
                mascotaRepository.save(new Mascota("Oreo", "4", "5", "Enfermo", "Infección respiratoria", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Cleo", "3", "4.5", "Enfermo", "Insuficiencia renal", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Leo", "5", "6", "Enfermo", "Cálculos urinarios", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Sasha", "7", "5", "Enfermo", "Enfermedad periodontal", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Milo", "4", "5.5", "Enfermo", "Alergia alimentaria", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Simba", "5", "4.2", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Misty", "3", "3.8", "Enfermo", "Infección urinaria", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Whiskers", "6", "5.0", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Lola", "8", "4.5", "Sano", "Ninguna", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Charlie", "4", "6.2", "Enfermo", "Alergia a alimentos", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Pepper", "5", "4.1", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Nala", "3", "3.9", "Enfermo", "Problemas dentales", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Zeus", "7", "5.5", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "9", "4.8", "Enfermo", "Obesidad", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Buddy", "4", "5.5", "Enfermo", "Alergia alimentaria", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                                
                mascotaRepository.save(new Mascota("Jack", "4", "5.0", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Minnie", "3", "3.5", "Enfermo", "Infección respiratoria", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Smokey", "5", "4.8", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Sandy", "8", "6.2", "Enfermo", "Obesidad", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Rusty", "2", "5.5", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Milo", "4", "4.0", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Oreo", "3", "4.1", "Enfermo", "Alergia alimentaria", "Macho", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Cleo", "6", "5.0", "Sano", "Ninguna", "Hembra", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Tommy", "9", "6.0", "Enfermo", "Problemas cardíacos", "Macho", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Zara", "5", "5.3", "Sano", "Ninguna", "Hembra", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Dexter", "4", "5.2", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "3", "4.0", "Enfermo", "Infección respiratoria", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Oscar", "5", "4.8", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Penny", "8", "6.0", "Enfermo", "Obesidad", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Fritz", "2", "5.5", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Juno", "4", "4.4", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Milo", "3", "4.2", "Enfermo", "Alergia alimentaria", "Macho", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Bella", "6", "5.3", "Sano", "Ninguna", "Hembra", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Rex", "9", "6.1", "Enfermo", "Problemas cardíacos", "Macho", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Luna", "5", "5.5", "Sano", "Ninguna", "Hembra", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Nina", "4", "4.5", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Cleo", "3", "3.9", "Enfermo", "Infección viral", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Leo", "5", "5.1", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Sasha", "8", "6.3", "Enfermo", "Diabetes", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Max", "2", "5.4", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Toby", "4", "4.0", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Rex", "3", "5.1", "Enfermo", "Problemas digestivos", "Macho", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Oreo", "5", "4.5", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Trixy", "8", "6.0", "Enfermo", "Diabetes", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Manchas", "2", "5.8", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Pip", "4", "4.2", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Chloe", "3", "3.8", "Enfermo", "Infección urinaria", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Toby", "5", "4.9", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Lola", "9", "6.5", "Enfermo", "Hipertensión", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Sam", "6", "5.2", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Jasper", "4", "4.0", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Fifi", "3", "3.5", "Enfermo", "Alergia", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Tiger", "5", "4.8", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Ella", "8", "5.0", "Enfermo", "Problemas renales", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Marty", "2", "5.5", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Jack", "4", "4.1", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Penny", "3", "3.9", "Enfermo", "Infección respiratoria", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Ginger", "5", "4.7", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Luna", "8", "5.4", "Enfermo", "Obesidad", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Rufus", "2", "5.3", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Lily", "4", "4.2", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Oscar", "3", "3.8", "Enfermo", "Infección de oído", "Macho", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Sammy", "5", "4.6", "Sano", "Ninguna", "Hembra", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "8", "5.7", "Enfermo", "Hipotiroidismo", "Macho", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Cleo", "2", "5.0", "Sano", "Ninguna", "Hembra", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Dexter", "4", "4.0", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Luna", "3", "3.8", "Enfermo", "Problemas respiratorios", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Charlie", "5", "4.5", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "8", "6.0", "Enfermo", "Diabetes", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Rusty", "2", "5.1", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Oscar", "4", "4.2", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Zara", "3", "3.9", "Enfermo", "Infección", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Buddy", "5", "4.8", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Ellie", "8", "5.5", "Enfermo", "Hipertensión", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Max", "6", "5.0", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                                
                mascotaRepository.save(new Mascota("Ziggy", "4", "4.0", "Sano", "Ninguna", "Macho", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Nina", "3", "3.5", "Enfermo", "Infección respiratoria", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Finn", "5", "4.8", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Lola", "9", "6.3", "Enfermo", "Obesidad", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Leo", "3", "5.2", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Misty", "4", "4.1", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Gizmo", "3", "4.5", "Enfermo", "Alergia alimentaria", "Macho", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Apollo", "5", "4.9", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Nala", "9", "6.0", "Enfermo", "Problemas dentales", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Max", "2", "5.5", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                
                mascotaRepository.save(new Mascota("Luna", "4", "4.3", "Sano", "Ninguna", "Hembra", "Birmano", 
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                mascotaRepository.save(new Mascota("Daisy", "3", "3.6", "Enfermo", "Infección de piel", "Hembra", "Persa", 
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Buster", "5", "4.7", "Sano", "Ninguna", "Macho", "Burmés", 
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                mascotaRepository.save(new Mascota("Mia", "9", "6.4", "Enfermo", "Artrosis", "Hembra", "Ragdoll", 
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                mascotaRepository.save(new Mascota("Rusty", "2", "5.0", "Sano", "Ninguna", "Macho", "Siberiano", 
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                // VETERINARIOS
                veterinarioRepository.save(new Veterinario("Sergio", "123456789", "s@t.com", "1234",
                                "https://i.ibb.co/0qX2b8t/sergio.jpg"));
                veterinarioRepository.save(new Veterinario("Carlos", "987654321", "c@vets.com", "2345",
                                "https://i.ibb.co/1JZfv1Z/carlos.jpg"));
                veterinarioRepository.save(new Veterinario("Laura", "456789123", "l@vetmail.com", "3456",
                                "https://i.ibb.co/Zh2ZDS3/laura.jpg"));
                veterinarioRepository.save(new Veterinario("Andrés", "321456987", "a@clinic.com", "4567",
                                "https://i.ibb.co/YktjXzK/andres.jpg"));
                veterinarioRepository.save(new Veterinario("Fernanda", "654321789", "f@catvets.com", "5678",
                                "https://i.ibb.co/Ms1FbJZ/fernanda.jpg"));
                veterinarioRepository.save(new Veterinario("Ricardo", "123987654", "r@medvet.com", "6789",
                                "https://i.ibb.co/LdWh1rX/ricardo.jpg"));
                veterinarioRepository.save(new Veterinario("Paula", "789456123", "p@vetservice.com", "7890",
                                "https://i.ibb.co/ZXWJLSB/paula.jpg"));
                veterinarioRepository.save(new Veterinario("María", "321654987", "m@vetclinic.com", "8901",
                                "https://i.ibb.co/F7bc0Z8/maria.jpg"));
                veterinarioRepository.save(new Veterinario("Javier", "456123789", "j@animalhealth.com", "9012",
                                "https://i.ibb.co/WghgDxX/javier.jpg"));
                veterinarioRepository.save(new Veterinario("Ana", "789123456", "ana@vetcare.com", "0123",
                                "https://i.ibb.co/qW0tdBh/ana.jpg"));
                veterinarioRepository.save(new Veterinario("Pedro", "987321654", "pedro@cathealth.com", "1235",
                                "https://i.ibb.co/0r5X4B3/pedro.jpg"));
                veterinarioRepository.save(new Veterinario("Daniela", "654789123", "daniela@felineclinic.com", "2346",
                                "https://i.ibb.co/t2X5PCm/daniela.jpg"));
                veterinarioRepository.save(new Veterinario("Gustavo", "123789654", "gustavo@animalclinic.com", "3457",
                                "https://i.ibb.co/Fqf7vLY/gustavo.jpg"));
                veterinarioRepository.save(new Veterinario("Lucía", "789321456", "lucia@vetprofession.com", "4568",
                                "https://i.ibb.co/1Zh7rdV/lucia.jpg"));
                veterinarioRepository.save(new Veterinario("Mauricio", "321987654", "mauricio@petcare.com", "5679",
                                "https://i.ibb.co/HqKNPP5/mauricio.jpg"));
                veterinarioRepository.save(new Veterinario("Adriana", "654123987", "adriana@vetservice.com", "6780",
                                "https://i.ibb.co/hgQkWdk/adriana.jpg"));
                veterinarioRepository.save(new Veterinario("Diego", "987456123", "diego@animaldoctor.com", "7891",
                                "https://i.ibb.co/LJSx78r/diego.jpg"));
                veterinarioRepository.save(new Veterinario("Claudia", "456789321", "claudia@clinicvet.com", "8902",
                                "https://i.ibb.co/MBSM4pj/claudia.jpg"));

                especialidadRepository.save(new Especialidad("Ortopédico", "Tratamiento de las articulaciones"));
                especialidadRepository.save(new Especialidad("Dermatología", "Diagnóstico y tratamiento de enfermedades de la piel"));
                especialidadRepository.save(new Especialidad("Odontología Veterinaria", "Cuidado y tratamiento dental"));
                especialidadRepository.save(new Especialidad("Cardiología", "Diagnóstico y tratamiento de enfermedades del corazón"));
                especialidadRepository.save(new Especialidad("Oncología", "Diagnóstico y tratamiento de cáncer en animales"));
                especialidadRepository.save(new Especialidad("Medicina Interna", "Diagnóstico y tratamiento de enfermedades internas"));
                especialidadRepository.save(new Especialidad("Reproducción y Obstetricia", "Manejo de la reproducción y cuidado durante el parto"));
                especialidadRepository.save(new Especialidad("Anestesiología", "Administración de anestesia y manejo del dolor"));
                especialidadRepository.save(new Especialidad("Nutrición Veterinaria", "Asesoría en dietas y alimentación para mascotas"));
                especialidadRepository.save(new Especialidad("Patología Veterinaria", "Estudio de enfermedades en animales"));
                especialidadRepository.save(new Especialidad("Veterinaria Forense", "Investigación de delitos relacionados con animales"));
                especialidadRepository.save(new Especialidad("Salud Pública Veterinaria", "Prevención y control de enfermedades zoonóticas"));

                // Tratamientos
                tratamientoRepository.save(new Tratamiento("Tratamiento antiinflamatorio",
                                "Reducción de inflamación y dolor", LocalDate.of(2023, 9, 1)));
                tratamientoRepository.save(new Tratamiento("Tratamiento antibiótico",
                                "Eliminación de infección bacteriana", LocalDate.of(2023, 9, 2)));
                tratamientoRepository.save(new Tratamiento("Tratamiento dermatológico",
                                "Tratamiento para problemas de piel", LocalDate.of(2023, 9, 3)));
                tratamientoRepository.save(new Tratamiento("Vacunación", "Aplicación de vacunas rutinarias",
                                LocalDate.of(2023, 9, 4)));
                tratamientoRepository.save(new Tratamiento("Desparasitación",
                                "Eliminación de parásitos internos y externos", LocalDate.of(2023, 9, 5)));
                tratamientoRepository.save(new Tratamiento("Tratamiento para la ansiedad",
                                "Manejo de ansiedad en mascotas", LocalDate.of(2023, 9, 6)));
                tratamientoRepository.save(new Tratamiento("Tratamiento hormonal",
                                "Regulación de desequilibrios hormonales", LocalDate.of(2023, 9, 7)));
                tratamientoRepository.save(new Tratamiento("Cirugía menor", "Procedimientos quirúrgicos menores",
                                LocalDate.of(2023, 9, 8)));
                tratamientoRepository.save(new Tratamiento("Tratamiento para la obesidad", "Plan de reducción de peso",
                                LocalDate.of(2023, 9, 9)));
                tratamientoRepository.save(new Tratamiento("Rehabilitación física",
                                "Terapias para recuperación de movilidad", LocalDate.of(2023, 9, 10)));

                administradorRepository.save(new Administrador("Arley", "1111"));
                administradorRepository.save(new Administrador("Kevin", "2222"));
                administradorRepository.save(new Administrador("Daniel", "3333"));

                // Drogas
                excelService.cargarDrogasDesdeExcel();

                // Asignar mascotas aleatoriamente a clientes
                List<Cliente> clientes = clienteRepository.findAll();
                List<Mascota> mascotas = mascotaRepository.findAll();
                List<Veterinario> veterinarios = veterinarioRepository.findAll();
                List<Especialidad> especialidades = especialidadRepository.findAll();
                List<Tratamiento> tratamientos = tratamientoRepository.findAll();
                List<Droga> drogas = drogaRepository.findAll();

                // Asocia aleatoriamente dos especialidades a cada veterinario.
                asignarEspecialidadesAVeterinarios(veterinarios, especialidades);

                // Asocia aleatoriamente dos mascotas a cada cliente
                asignarMascotasAClientes(clientes, mascotas);

                asignarRelacionesATratamientos(veterinarios, tratamientos, drogas, mascotas);

        }

        private void asignarRelacionesATratamientos(List<Veterinario> veterinarios, List<Tratamiento> tratamientos, List<Droga> drogas, List<Mascota> mascotas) {
                Random random = new Random();

                for (Tratamiento tratamiento : tratamientos) {
                        tratamiento.setVeterinario(veterinarios.get(random.nextInt(veterinarios.size())));
                        tratamiento.setDroga(drogas.get(random.nextInt(drogas.size())));
                        tratamiento.setMascota(mascotas.get(random.nextInt(mascotas.size())));
                }

        }

        private void asignarEspecialidadesAVeterinarios(List<Veterinario> veterinarios, List<Especialidad> especialidades) {
                Random random = new Random();

                for (Veterinario veterinario : veterinarios) {
                        if (veterinario != null) {
                                int numEspecialidades = random.nextInt(2) + 1;

                                for (int i = 0; i < numEspecialidades; i++) {
                                        Especialidad especialidad = especialidades.get(random.nextInt(especialidades.size()));
                                        veterinario.agregarEspecialidad(especialidad);
                                }
                        }
                }
        }

        private void asignarMascotasAClientes(List<Cliente> clientes, List<Mascota> mascotas) {
                Random random = new Random();
                List<Mascota> mascotasDisponibles = new ArrayList<>(mascotas);

                for (Cliente cliente : clientes) {
                        if (!(mascotasDisponibles.size() < 2) && cliente != null) {
                                // Selecciona dos mascotas aleatorias
                                List<Mascota> mascotasSeleccionadas = random.ints(0, mascotasDisponibles.size())
                                                .distinct()
                                                .limit(2)
                                                .mapToObj(mascotasDisponibles::get)
                                                .collect(Collectors.toList());

                                // Asocia las mascotas seleccionadas con el cliente
                                for (Mascota mascota : mascotasSeleccionadas) {
                                        mascota.setCliente(cliente);
                                        mascotasDisponibles.remove(mascota); // Elimina la mascota de la lista de
                                                                             // disponibles
                                }
                        }
                }
        }
}
