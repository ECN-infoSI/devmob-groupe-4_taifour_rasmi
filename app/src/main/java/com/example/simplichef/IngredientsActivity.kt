package com.example.simplichef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplichef.ui.theme.SimpliChefTheme

class IngredientsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpliChefTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageChoixIngredients()
                }
            }
        }
    }
}


@Composable
fun PageChoixIngredients() {
    // État pour la recherche
    var searchQuery by remember { mutableStateOf("") }

    // Liste d'ingrédients à sélectionner
    val ingredients = listOf(
        "Ail",
        "Artichauts",
        "Asperge blanche",
        "Asperge verte",
        "Aubergine",
        "Bette",
        "pommes de terre"
    )

    // État pour les ingrédients sélectionnés
    var selectedIngredients by remember { mutableStateOf(setOf<String>()) }

    // Catégories de filtres
    val categories = listOf(
        "Légumes" to true,
        "Viande" to false,
        "Épices et herbes" to false,
        "Laitiers" to false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F0)) // Fond légèrement beige
    ) {
        // Contenu principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barre du haut
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Flèche retour
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                    modifier = Modifier.size(24.dp)
                )

                // Titre
                Text(
                    text = "SimpliChef",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )

                // Image de profil
                Image(
                    painter = painterResource(R.drawable.profil),
                    contentDescription = "Photo de profil",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // Filtres de catégories
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { (category, isSelected) ->
                    Categories(
                        text = category,
                        isSelected = isSelected,
                        onClick = { /* Changer de catégorie */ }
                    )
                }
            }

            // Barre de recherche
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recherche",
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )

                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { searchQuery = "" },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Effacer",
                            tint = Color.Gray
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Rechercher",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Liste d'ingrédients
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ingredients) { ingredient ->
                    IngredientItem(
                        name = ingredient,
                        isSelected = selectedIngredients.contains(ingredient),
                        onToggle = {
                            selectedIngredients = if (selectedIngredients.contains(ingredient)) {
                                selectedIngredients - ingredient
                            } else {
                                selectedIngredients + ingredient
                            }
                        }
                    )
                }
            }

            // Bouton confirmer
            Button(
                onClick = { /* Action de confirmation */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Confirmer",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    }
}

@Composable
fun Categories(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(
                if (isSelected) Color(0xFFFFB800) else Color.White
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun IngredientItem(
    name: String,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontSize = 16.sp
            )

            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.LightGray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientSelectionPreview() {
    SimpliChefTheme {
        PageChoixIngredients()
    }
}